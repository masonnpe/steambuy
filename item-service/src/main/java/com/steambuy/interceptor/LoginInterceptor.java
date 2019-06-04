package com.steambuy.interceptor;

import com.steambuy.common.model.UserInfo;
import com.steambuy.common.utils.CookieUtils;
import com.steambuy.common.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request,"token");
        if (StringUtils.isBlank(token)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        try{
            UserInfo userInfo= TokenUtil.parseToken(token);
            String newToken = TokenUtil.createToken(userInfo);
            CookieUtils.setCookie(request, response, "token", token, 100000);
            userInfoThreadLocal.set(userInfo);
            return true;
        }catch (Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        userInfoThreadLocal.remove();
    }

    public static UserInfo getUserInfo(){
        return userInfoThreadLocal.get();
    }
}
