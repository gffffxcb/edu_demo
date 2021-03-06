package com.mgh.cms.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.cms.entity.CrmBanner;
import com.mgh.cms.service.BannerService;
import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author MGH
 * @create 2022-0223 10:25 上午
 */
@RestController("bannerFrontController")
@RequestMapping("/cms/bannerFront")
@CrossOrigin
@Slf4j
@Api("banner前台业务")
public class BannerFrontController {
    @Autowired
    private BannerService bannerService;

    @GetMapping
    @ApiOperation("获取所有排序最小的前6条banner")
    public MyResult getSixBanner(){
        List<Map<String, Object>> banners = bannerService.getSixBanner();
        if (!banners.isEmpty()){
            return MyResult.ok().data("items",banners);
        }
        return MyResult.error();
    }
}
