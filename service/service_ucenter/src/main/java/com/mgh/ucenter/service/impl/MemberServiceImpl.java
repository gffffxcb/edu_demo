package com.mgh.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.commanUtils.JwtUtil;
import com.mgh.commanUtils.to.OrderInfoMemberTo;
import com.mgh.serviceBase.exception.MyException;
import com.mgh.ucenter.entity.Member;
import com.mgh.ucenter.entity.vo.LoginVo;
import com.mgh.ucenter.entity.vo.RegisterVo;
import com.mgh.ucenter.mapper.MemberMapper;
import com.mgh.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-24
 */
@Service("memberServiceImpl")
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVo login) {
        String mobile = login.getMobile();
        String password = login.getPassword();
        //校验参数
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new MyException(20001, "参数为空");
        }
        //获取会员
        Member member = memberMapper.selectOne(new QueryWrapper<Member>().eq("mobile", mobile));
        if (null == member) {
            throw new MyException(20001, "该用户不存在");
        }
        //校验密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(member.getPassword())) {
            throw new MyException(20001, "密码不正确");
        }
        //校验是否被禁用
        if (member.getIsDisabled() == 1) {
            throw new MyException(20001, "该账号已被禁用");
        }
        //使用JWT生成token字符串
        String token = JwtUtil.getJwtToken(member.getId(), member.getNickname());
        return token;
    }


    @Override
    public String register(RegisterVo registerVo) {
        String s = DigestUtils.md5DigestAsHex("1234".getBytes()); //md5加密
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new MyException(20001, "参数为空");
        }
        //校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(mobleCode)) {
            throw new MyException(20001, "验证码已经失效");
        }
        //查询数据库中是否存在相同的手机号码
        Integer count = memberMapper.selectCount(new QueryWrapper<Member>().eq("mobile", mobile));
        if (count.intValue() > 0) {
            throw new MyException(20001, "手机号已存在");
        }
        //添加注册信息到数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        //默认头像
        member.setAvatar("https://edu-online-manager.oss-cn-hangzhou.aliyuncs.com/edu-online/avatar/default.jpg");
        int insert = memberMapper.insert(member);
        if (insert>=1){
            String token = JwtUtil.getJwtToken(member.getId(), member.getNickname());
            return token;
        }
        return null;
    }

    @Override
    public Member getByOpenId(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        Member member = memberMapper.selectOne(queryWrapper);
        return member;
    }

    @Override
    public OrderInfoMemberTo getOrderInfoByMember(String id) {
        OrderInfoMemberTo result=  memberMapper.getOrderInfoByMember(id);
        return result;
    }

    @Override
    public Integer getRegisterCount(String day) {
       Integer result= memberMapper.getRegisterCount(day);
        return result;
    }
}
