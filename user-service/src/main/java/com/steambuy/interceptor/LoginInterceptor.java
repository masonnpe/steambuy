package com.steambuy.interceptor;

import com.steambuy.common.model.UserInfo;
import com.steambuy.common.utils.CookieUtils;
import com.steambuy.common.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = CookieUtils.getCookieValue(request,"token");
        if (StringUtils.isBlank(token)){
            //2.未登录，返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        //3.有token，查询用户信息
        try{
            //4.解析成功，说明已经登录
            UserInfo userInfo= TokenUtil.parseToken(token);
            // 刷新时间
            String newToken = TokenUtil.createToken(userInfo);
            CookieUtils.setCookie(request, response, "token", token, 100000);
            //5.放入线程域
            userInfoThreadLocal.set(userInfo);
            return true;
        }catch (Exception e){
            //6.抛出异常，证明未登录，返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        userInfoThreadLocal.remove();
    }

    public static UserInfo getUserInfo(){
        return userInfoThreadLocal.get();
    }
}
