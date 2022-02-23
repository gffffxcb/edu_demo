package com.mgh.cms.mapper;

import com.mgh.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 首页banner表 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@Repository("bannerMapper")
public interface BannerMapper extends BaseMapper<CrmBanner> {

}
