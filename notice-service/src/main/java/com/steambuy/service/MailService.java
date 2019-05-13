package com.steambuy.service;

import com.steambuy.common.utils.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Slf4j
public class MailService {

    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String mail;

    public boolean sendEmail(String email,String code){
        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(mail);
            message.setTo(email);
            message.setSubject("验证码");
            message.setText(code);
            mailSender.send(message);
        }catch (Exception e){
            log.info("邮件发送异常");
            return false;
        }
        return true;
    }
}
