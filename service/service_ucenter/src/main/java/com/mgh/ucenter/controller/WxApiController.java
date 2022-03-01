package com.mgh.ucenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.mgh.commanUtils.HttpClientUtil;
import com.mgh.commanUtils.JwtUtil;
import com.mgh.serviceBase.exception.MyException;
import com.mgh.ucenter.entity.Member;
import com.mgh.ucenter.service.MemberService;
import com.mgh.ucenter.utils.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author MGH
 * @create 2022-0225 10:50 上午
 */
@CrossOrigin
@Controller//注意这里没有配置 RestController
@RequestMapping("/api/ucenter/wx")
@Api("微信登录接口")
@Slf4j
public class WxApiController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/getLoginCode")
    @ApiOperation("生成微信二维码")
    public String getWxCode(HttpServletRequest httpServletRequest) {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        // 回调地址
        String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new MyException(20001, e.getMessage());
        }
        // 防止csrf攻击(跨站请求伪造攻击)
        String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        // 采用redis等进行缓存state 使用sessionId为key 10分钟后过期，可配置 //键:"weChat-open-state-" + httpServletRequest.getSession().getId() //值:state
        redisTemplate.opsForValue().set("weChat-open-state-" + httpServletRequest.getSession().getId(), state, 10, TimeUnit.MINUTES);
        //过期时间:30分钟
        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl,
                state);
        return "redirect:" + qrcodeUrl;
    }

    @GetMapping("/callback")
    @ApiOperation("获取用户信息并登陆跳转首页")
    public String getWxCenterInfo(String code, String state, HttpServletRequest httpServletRequest) {
        String rState = redisTemplate.opsForValue().get("weChat-open-state-" + httpServletRequest.getSession().getId());
        //1、判断status状态是否和redis中一致
        if (rState == null || !rState.equals(state)) {
            log.info("status已失效返回登陆页面");
            return "redirect:http://localhost:3000/login"; //失效返回登陆页面
        }
        //2、判断code是否为空
        if (code == null || code.equals("")) {
            log.info("出现异常返回登陆页面");
            return "redirect:http://localhost:3000/login"; //出现异常返回登陆页面
        }
        //3、拼接第二次请求访问url
        String baseUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String getUserUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code);
        //4、使用httpclientutil请求并接收accessToken
        JSONObject jsonObject;
        try {
            jsonObject = HttpClientUtil.httpGet(getUserUrl);
        } catch (Exception e) {
            throw new MyException(20001, "获取access_token失败");
        }
        //5、获取json中信息
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        //6、查询是否是新用户
        Member member = memberService.getByOpenId(openid);
        String token = "";
        if (member == null) { //新用户就再次发送请求获取用户信息，并新建插入数据库
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            JSONObject userInfoJson;
            try {
                userInfoJson = HttpClientUtil.httpGet(userInfoUrl);
            } catch (Exception e) {
                throw new MyException(20001, "获取用户信息失败");
            }
            if (!userInfoJson.isEmpty()) {
                Member addMember = new Member();
                addMember.setOpenid(userInfoJson.getString("openid"));
                addMember.setNickname(userInfoJson.getString("nickname"));
                addMember.setSex((Integer) userInfoJson.get("sex"));
                addMember.setAvatar(userInfoJson.getString("headimgurl"));
                memberService.save(addMember);
                //新用户生成用户token以及用户id/name返回首页
                token = JwtUtil.getJwtToken(addMember.getId(), addMember.getNickname());
                return "redirect:http://localhost:3000?token=" + token;
            }
        }
        //生成用户token以及用户id/name返回首页
        token = JwtUtil.getJwtToken(member.getId(), member.getNickname());
        return "redirect:http://localhost:3000?token=" + token;
    }

}
