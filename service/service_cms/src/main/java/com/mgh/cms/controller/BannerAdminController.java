package com.mgh.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.cms.entity.CrmBanner;
import com.mgh.cms.service.BannerService;
import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@RestController("bannerAdminController")
@RequestMapping("/cms/bannerAdmin")
@CrossOrigin
@Slf4j
@Api("banner后台管理")
public class BannerAdminController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation("获取所有banner信息")
    @PostMapping("/{nowPage}/{pageSize}")//后台获取所有banner新信息列表,根据条件分页
    public MyResult getBannerList(@PathVariable(value = "nowPage") Integer nowPage, @PathVariable(value = "pageSize") Integer pageSize, @RequestParam(required = false) String title) {
        Page<CrmBanner> page = new Page<>(nowPage, pageSize);
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        if (title!=null) {
            queryWrapper.like("title", title);
        } else {
            queryWrapper = null;
        }
        Page<CrmBanner> bannerPage = bannerService.page(page, queryWrapper);
        if (bannerPage != null) {
            return MyResult.ok().data("items", bannerPage.getRecords()).data("total", bannerPage.getTotal()).message("查询成功");
        }
        return MyResult.error().message("查询失败");
    }

    @ApiOperation("根据Id获取banner信息")
    @GetMapping("/{bannerId}")
    public MyResult getBannerById(@PathVariable(value = "bannerId") String bannerId) {
        CrmBanner banner = bannerService.getById(bannerId);
        if (banner!=null){
            return MyResult.ok().data("info",banner);
        }
        return MyResult.error();
    }

    @ApiOperation("添加banner信息")
    @PostMapping
    public MyResult addBanner(@RequestBody CrmBanner banner) {
        log.info(banner.toString());
        boolean result = bannerService.save(banner);
        if (result){
            return MyResult.ok().message("添加成功");
        }
        return MyResult.error().message("更新失败");
    }

    @ApiOperation("修改banner信息")
    @PutMapping
    public MyResult updateBanner(@RequestBody CrmBanner banner) {
        log.info(banner.toString());
        boolean result = bannerService.updateById(banner);
        if (result){
            return MyResult.ok().message("更新成功");
        }
        return MyResult.error().message("更新失败");
    }
    @ApiOperation("删除banner信息")
    @DeleteMapping("/{bannerId}")
    public MyResult deleteBannerById(@PathVariable(value = "bannerId")String bannerId) {
        boolean result = bannerService.removeById(bannerId);
        if (result){
            return MyResult.ok().message("删除成功");
        }
        return MyResult.error().message("删除失败");
    }

}

