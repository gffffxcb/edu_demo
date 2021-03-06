package com.mgh.msm.service.impl;

import com.mgh.msm.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

/**
 * @author MGH
 * @create 2022-0224 11:58 上午
 */
@Service("mailServiceImpl")
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    /**
     * 用来发送模版邮件
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${spring.mail.username}")
    private String from; //发送人

    @Override
    public Boolean sendMail(String toMail) {
        String code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(from);// 发送人的邮箱
            messageHelper.setTo(toMail);//发给谁  对方邮箱
            messageHelper.setSubject("登陆验证"); //标题
            //使用模板thymeleaf
            //Context是导这个包import org.thymeleaf.context.Context;
            Context context = new Context();
            //定义模板数据
            context.setVariable("project", "元宇宙公司");
            context.setVariable("author", "mgh");
            context.setVariable("code", code);
            //获取thymeleaf的html模板
            String emailContent = templateEngine.process("mail",context); //指定模板路径 templates下的mail。html
            messageHelper.setText(emailContent,true);
            //发送邮件
            javaMailSender.send(mimeMessage);
            //添加进缓存
            redisTemplate.opsForValue().set(toMail,code,5, TimeUnit.MINUTES);
            return true;
        } catch (MessagingException e) {
            log.error("模板邮件发送失败->message:{}",e.getMessage());
            return false;
        }
    }
}
