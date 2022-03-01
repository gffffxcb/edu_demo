package com.mgh.order.mapper;

import com.mgh.order.entity.TPayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-28
 */
@Repository("tPayLogMapper")
public interface TPayLogMapper extends BaseMapper<TPayLog> {

}
