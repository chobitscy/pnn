package com.pn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.annotation.BaseResponse;
import com.pn.annotation.LoginUser;
import com.pn.service.FollowService;
import com.pn.support.Query;
import com.pn.utils.UserUtil;
import com.pn.vo.FollowVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 关注接口控制器
 **/
@RestController
@RequestMapping("/follow")
@Api(value = "关注管理", tags = "关注接口")
@BaseResponse
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @LoginUser
    @PostMapping("/follow")
    @ApiOperation("关注")
    public Boolean follow(Long pid, HttpServletRequest request) {
        return followService.follow(pid, UserUtil.getUserIdByRequest(request));
    }

    @LoginUser
    @PostMapping("/unFollow")
    @ApiOperation("取关")
    public Boolean unFollow(Long pid, HttpServletRequest request) {
        return followService.unFollow(pid, UserUtil.getUserIdByRequest(request));
    }

    @LoginUser
    @GetMapping("/page")
    @ApiOperation("已关注列表")
    public IPage<FollowVo> selectByPage(Query query, HttpServletRequest request) {
        return followService.selectByPage(query, UserUtil.getUserIdByRequest(request));
    }
}
