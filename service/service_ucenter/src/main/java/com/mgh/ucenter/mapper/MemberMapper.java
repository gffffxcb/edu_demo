package com.mgh.ucenter.mapper;

import com.mgh.commanUtils.to.OrderInfoMemberTo;
import com.mgh.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-24
 */
@Repository("memberMapper")
public interface MemberMapper extends BaseMapper<Member> {

    OrderInfoMemberTo getOrderInfoByMember(String id);

    Integer getRegisterCount(String day);
}
