package com.steambuy.listener;

import com.steambuy.service.MailService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class NoticeListener {

    @Resource
    private MailService mailService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "notice.verify.code", durable = "true"), //队列持久化
            exchange = @Exchange(
                    value = "steambuy.notice.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"verify.code"}
    ))
    public void listenCreate(Map<String,String> msg) throws Exception {

        mailService.sendEmail("841376896@qq.com","");
    }
}