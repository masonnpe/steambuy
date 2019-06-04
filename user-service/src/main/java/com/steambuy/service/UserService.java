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




    public User queryUser(String username, String password) {
        User user=userMapper.queryUser(username,password);
        if(user==null){
            return null;
        }
        System.out.println(MD5Util.formPassToDBPass(password,username));
        if (user.getPassword().equals(MD5Util.formPassToDBPass(password,username))) {
            return user;
        }else {
            return null;
        }
    }
}
