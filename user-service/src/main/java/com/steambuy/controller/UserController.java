package com.steambuy.controller;

import com.steambuy.common.model.ResponseEntity;
import com.steambuy.common.utils.UUIDUtil;
import com.steambuy.vo.LoginVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    @ResponseBody
    public String hello(@PathVariable Long id){
        return "hehe";
    }

    @RequestMapping("/login.html")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@Valid LoginVo loginVo){
        loginVo.getPassword();
        Cookie cookie=new Cookie("access-token", UUIDUtil.uuid());
        return ResponseEntity.success("");
    }
}
