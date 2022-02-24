package com.mgh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.cms.entity.CrmBanner;
import com.mgh.cms.mapper.BannerMapper;
import com.mgh.cms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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


    @Cacheable(value = "banner", key = "'getSixBanner'")
    @Override
    public List<Map<String, Object>> getSixBanner() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title", "image_url", "link_url");
        wrapper.orderByAsc("sort");
        wrapper.last("limit 6");
        List<Map<String, Object>> maps = bannerMapper.selectMaps(wrapper);
        return maps;
    }
}
