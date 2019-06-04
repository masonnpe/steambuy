package com.steambuy.controller;

import com.steambuy.common.model.UserInfo;
import com.steambuy.common.utils.CookieUtils;
import com.steambuy.common.utils.MD5Util;
import com.steambuy.common.utils.TokenUtil;
import com.steambuy.model.User;
import com.steambuy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("code")
    public ResponseEntity sendVerifyCode(@RequestParam("email") String email){
        Boolean result = userService.sendVerifyCode(email);
        if (result == null || !result){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid @RequestBody User user, BindingResult bindingResult, @RequestParam("code") String code){
        bindingResult.getAllErrors().get(0).getDefaultMessage();
        Boolean result = userService.register(user,code);
        if(result == null || !result){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("query")
    public ResponseEntity<User> queryUser(@RequestParam String username,@RequestParam String password){
        User user = this.userService.queryUser(username,password);
        if (user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("login.html")
    public String loginPage(){
        return "login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public String login( User user, HttpServletRequest request, HttpServletResponse response){
        System.out.println(user);
        User info=userService.queryUser(user.getUsername(),user.getPassword());
        if(info==null){
            return "false";
        }
        String token = TokenUtil.createToken(new UserInfo(info.getId(), info.getUsername()));
        CookieUtils.setCookie(request, response, "token", token, 100000);
        return "true";
    }

}
