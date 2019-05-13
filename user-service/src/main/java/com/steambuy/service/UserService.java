package com.steambuy.service;

import com.steambuy.common.model.UserInfo;
import com.steambuy.common.utils.JsonUtils;
import com.steambuy.common.utils.MD5Util;
import com.steambuy.common.utils.NumberUtils;
import com.steambuy.common.utils.TokenUtil;
import com.steambuy.mapper.UserMapper;
import com.steambuy.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String CODE_PREFIX = "user:code:email";

    public Boolean checkData(String data, Integer type) {
        User user = new User();
        switch (type) {
            case 1:
                user.setUsername(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            default:
                return null;
        }
        return this.userMapper.selectCount(user) == 0;
    }

    public Boolean sendVerifyCode(String email) {
        String code = NumberUtils.generateCode(6);
        try {
            Map<String, String> msg = new HashMap<>();
            msg.put("email", email);
            msg.put("code", code);
            amqpTemplate.convertAndSend("steambuy.notice.exchange", "notice.verify.code", msg);
            stringRedisTemplate.opsForValue().set(CODE_PREFIX+email,code,5,TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            log.error("发送邮件失败 email:{},code:{}", email, code);
            return false;
        }
    }

    public Boolean register(User user, String code) {
        String key = CODE_PREFIX + user.getEmail();
        String codeCache = stringRedisTemplate.opsForValue().get(key);
        if (!Objects.equals(codeCache,code)) {
            return false;
        }
        user.setCreated(new Date());
        String encodePassword = MD5Util.formPassToDBPass(user.getPassword(), user.getUsername());
        user.setPassword(encodePassword);
        boolean result = userMapper.insertSelective(user) == 1;
        if (result) {
            try {
                this.stringRedisTemplate.delete(CODE_PREFIX + user.getEmail());
            } catch (Exception e) {
                log.error("删除缓存验证码失败，code:{}", code, e);
            }
        }
        return result;
    }


    public String authentication(String username, String password) {
        try{
            //1.调用微服务查询用户信息
            User user = userMapper.queryUser(username, password);
            //2.查询结果为空，则直接返回null
            if (user == null){
                return null;
            }
            //3.查询结果不为空，则生成token
            String token = TokenUtil.createToken(new UserInfo(1L,""));
            return token;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public User queryUser(String username, String password) {
        /**
         * 逻辑改变，先去缓存中查询用户数据，查到的话直接返回，查不到再去数据库中查询，然后放入到缓存当中
         */
        //1.缓存中查询
        BoundHashOperations<String,Object,Object> hashOperations = this.stringRedisTemplate.boundHashOps("user:info");
        String userStr = (String) hashOperations.get(username);
        User user;
        if (StringUtils.isEmpty(userStr)){
            //在缓存中没有查到，去数据库查,查到放入缓存当中
            User record = new User();
            record.setUsername(username);
            user = this.userMapper.selectOne(record);
            hashOperations.put(user.getUsername(), JsonUtils.serialize(user));
        } else {
            user =  JsonUtils.parse(userStr,User.class);
        }


        //2.校验用户名
        if (user == null){
            return null;
        }
        //3. 校验密码
        boolean result =MD5Util.formPassToDBPass(username, password).equals(user.getPassword());
        if (!result){
            return null;
        }

        //4.用户名密码都正确
        return user;
    }
}
