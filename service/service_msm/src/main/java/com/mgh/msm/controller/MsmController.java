package com.mgh.msm.controller;

import com.mgh.commanUtils.MyResult;
import com.mgh.msm.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author MGH
 * @create 2022-0224 10:29 上午
 */
@RestController("msmController")
@RequestMapping("/msm/message")
@CrossOrigin
@Api("短信验证服务")
public class MsmController {

    @Autowired
    private MsmService msmService;
    @ApiOperation("6位验证码短信发送")
    @PostMapping("/{phone}")
    public MyResult sendMessage(@PathVariable(value = "phone") String phone) {
        String code = String.valueOf(((Math.random() * 9 + 1) * 100000));
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);
        map.put("phone",phone);
        Boolean result = msmService.sendMessage(phone, code, map);
        if (result){
            return MyResult.ok().message("发送成功");
        }
        return MyResult.error().message("发送失败");
    }
}
