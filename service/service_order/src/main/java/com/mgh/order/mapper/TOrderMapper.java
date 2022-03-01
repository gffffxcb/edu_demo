package com.mgh.order.mapper;

import com.mgh.order.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-28
 */
@Repository("tOrderMapper")
public interface TOrderMapper extends BaseMapper<TOrder> {

}
