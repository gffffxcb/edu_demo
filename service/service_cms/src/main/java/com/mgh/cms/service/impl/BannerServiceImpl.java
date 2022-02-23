package com.mgh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.cms.entity.CrmBanner;
import com.mgh.cms.mapper.BannerMapper;
import com.mgh.cms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@Service("bannerService")
public class BannerServiceImpl extends ServiceImpl<BannerMapper, CrmBanner> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        wrapper.last("limit 4");
        List<CrmBanner> banners = bannerMapper.selectList(wrapper);
        return banners;
    }
}
