package com.mgh.ucenter.controller;


import com.mgh.commanUtils.JwtUtil;
import com.mgh.commanUtils.MyResult;
import com.mgh.commanUtils.to.OrderInfoMemberTo;
import com.mgh.ucenter.entity.Member;
import com.mgh.ucenter.entity.vo.LoginVo;
import com.mgh.ucenter.entity.vo.RegisterVo;
import com.mgh.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-24
 */
@RestController("memberController")
@RequestMapping("/api/ucenter/member")
@Slf4j
@CrossOrigin
@Api("会员管理")
public class MemberController {
    //@JwtToken //自定义token拦截若无token则拒绝访问
    @Autowired
    private MemberService memberService;


    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public MyResult login(@RequestBody LoginVo login) {
        String token = memberService.login(login);
        if (token != null) {
            return MyResult.ok().data("token", token);
        }
        return MyResult.error();
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public MyResult register(@RequestBody RegisterVo registerVo) {
        String token = memberService.register(registerVo);
        if (token != null) {
            return MyResult.ok().data("token", token).message("注册成功");
        }
        return MyResult.error();
    }

    @ApiOperation(value = "从token获取信息")
    @GetMapping("/tokenInfo")
    public MyResult getInfoByToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        HashMap<String, String> info = JwtUtil.getIdByJwtToken(token);
        Member member = memberService.getById(info.get("id"));
        if (info != null) {
            return MyResult.ok().data("items", member);
        }
        return MyResult.error();
    }

    @ApiOperation(value = "order获取用户信息")
    @GetMapping("/orderInfo/{id}")
    public OrderInfoMemberTo getOrderInfoByMember(@PathVariable(value = "id") String id) {
        OrderInfoMemberTo result = memberService.getOrderInfoByMember(id);
        if (result != null) {
            return result;
        }
        return null;
    }

    @ApiOperation(value = "获取某天内注册人数")
    @GetMapping("/registerCount/{day}")
    public MyResult getRegisterCount(@PathVariable(value = "day") String day) {
        Integer result = memberService.getRegisterCount(day);
        return MyResult.ok().data("registerNum",result);
    }


}

