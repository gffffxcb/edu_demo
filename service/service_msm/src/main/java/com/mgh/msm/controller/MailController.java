package com.mgh.msm.controller;

import com.mgh.commanUtils.MyResult;
import com.mgh.msm.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MGH
 * @create 2022-0224 11:56 上午
 */
@RestController("mailController")
@RequestMapping("/msm/mail")
@CrossOrigin
@Api("邮箱验证服务")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/{e-mail}")
    @ApiOperation("6位验证码邮箱发送")
    public MyResult sendEmail(@PathVariable(value = "e-mail") String mail) {
       Boolean result= mailService.sendMail(mail);
       if (result){
           return MyResult.ok().message("发送成功");
       }
        return MyResult.ok().message("发送失败");
    }

}
