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
@RequestMapping("/start")
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
    @PostMapping("/start")
    @ApiOperation("收藏")
    public Boolean start(Long vid, HttpServletRequest request) {
        return starService.start(vid, UserUtil.getUserIdByRequest(request));
    }

    @LoginUser
    @DeleteMapping("/unStart/{id}")
    @ApiOperation("取消收藏")
    public Boolean unStart(@PathVariable Long id) {
        return starService.unStart(id);
    }

    @LoginUser
    @GetMapping("/check")
    @ApiOperation("检查")
    public Boolean check(Long vid, HttpServletRequest request) {
        return starService.check(vid, UserUtil.getUserIdByRequest(request));
    }
}
