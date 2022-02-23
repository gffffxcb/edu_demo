package com.mgh.edu.controller;

import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author MGH
 * @create 2022-0213 5:22 下午
 */
@Api(value = "登陆管理")
@RestController("loginController")
@CrossOrigin
@RequestMapping("/edu/user")
@Slf4j
public class LoginController {

    @PostMapping("/login")
    public MyResult loginIn(String userName, String passWord) {

        return MyResult.ok().data("token","admin");
    }

    @GetMapping("/info")
    public MyResult getUserInfo(String token) {
        return MyResult.ok().data("name","admin").data("roles","[admin]").data("avatar","https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3").data("introduction","aaaaaaaaaaaaaaaaaaa");
    }

}
