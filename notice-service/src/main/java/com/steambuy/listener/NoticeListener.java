package com.steambuy.listener;

import com.steambuy.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Slf4j
public class NoticeListener {

    @Resource
    private MailService mailService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "notice.verify.code", durable = "true"),
            exchange = @Exchange(
                    value = "steambuy.notice.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"verify.code"}
    ))
    public void listenCreate(Map<String,String> msg) throws Exception {
        log.info("mail:"+msg.get("mail")+",code:"+msg.get("code"));
        mailService.sendEmail(msg.get("mail"),msg.get("code"));
    }
}