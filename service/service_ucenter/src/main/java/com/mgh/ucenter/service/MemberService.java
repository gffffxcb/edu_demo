package com.mgh.ucenter.service;

import com.mgh.commanUtils.to.OrderInfoMemberTo;
import com.mgh.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.ucenter.entity.vo.LoginVo;
import com.mgh.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-24
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo login);

    String register(RegisterVo registerVo);

    Member getByOpenId(String openid);

    OrderInfoMemberTo getOrderInfoByMember(String id);

    Integer getRegisterCount(String day);
}
