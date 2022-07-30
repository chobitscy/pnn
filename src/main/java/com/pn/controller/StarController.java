package com.pn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.annotation.BaseResponse;
import com.pn.annotation.LoginUser;
import com.pn.service.StarService;
import com.pn.support.Query;
import com.pn.utils.UserUtil;
import com.pn.vo.StarVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏接口控制器
 **/
@RestController
@RequestMapping("/star")
@Api(value = "收藏管理", tags = "收藏接口")
@BaseResponse
@RequiredArgsConstructor
public class StarController {

    private final StarService starService;

    @LoginUser
    @GetMapping("/page")
    @ApiOperation("分页")
    public IPage<StarVo> selectByPage(Query query, HttpServletRequest request) {
        return starService.selectByPage(query, UserUtil.getUserIdByRequest(request));
    }

    @LoginUser
    @PostMapping("/add/{vid}")
    @ApiOperation("收藏")
    public Boolean add(@PathVariable Long vid, HttpServletRequest request) {
        return starService.add(vid, UserUtil.getUserIdByRequest(request));
    }

    @LoginUser
    @DeleteMapping("/{vid}")
    @ApiOperation("取消收藏")
    public Boolean unStart(@PathVariable Long vid, HttpServletRequest request) {
        return starService.unStart(vid, UserUtil.getUserIdByRequest(request));
    }

    @LoginUser
    @GetMapping("/check/{vid}")
    @ApiOperation("检查")
    public Boolean check(@PathVariable Long vid, HttpServletRequest request) {
        return starService.check(vid, UserUtil.getUserIdByRequest(request));
    }
}
