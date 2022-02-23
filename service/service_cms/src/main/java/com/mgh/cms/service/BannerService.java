package com.mgh.cms.service;

import com.mgh.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
public interface BannerService extends IService<CrmBanner> {
    List<CrmBanner> getAllBanner();
}
