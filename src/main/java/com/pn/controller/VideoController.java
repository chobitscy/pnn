package com.pn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.annotation.BaseResponse;
import com.pn.annotation.LoginUser;
import com.pn.dto.VideoDto;
import com.pn.service.VideoService;
import com.pn.support.Query;
import com.pn.support.validate.AddValidationGroup;
import com.pn.support.validate.EditValidationGroup;
import com.pn.vo.VideoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 视频接口控制器
 **/
@RestController
@RequestMapping("/video")
@Api(value = "视频管理", tags = "视频接口")
@BaseResponse
@AllArgsConstructor
public class VideoController {

    private VideoService videoService;

    @LoginUser
    @GetMapping("/popular/{day}")
    @ApiOperation("流行")
    public IPage<VideoVo> popular(Query query, @PathVariable @NotNull(message = "周期为空") Integer day) {
        return videoService.popular(query, day);
    }

    @LoginUser
    @GetMapping("/page")
    @ApiOperation("分页")
    public IPage<VideoVo> selectByPage(Query query) {
        return videoService.selectByPage(query);
    }

    @LoginUser
    @GetMapping("/search/{keyword}")
    @ApiOperation("搜索")
    public IPage<VideoVo> search(Query query, @PathVariable @NotNull(message = "关键词为空") String keyword) {
        return videoService.search(query, keyword);
    }

    @LoginUser
    @GetMapping("/{id}")
    @ApiOperation("详情")
    public VideoVo detail(@PathVariable Long id) {
        return videoService.detail(id);
    }

    @LoginUser
    @PutMapping("/mark")
    @ApiOperation("标记")
    public Boolean mark(@Validated(AddValidationGroup.class) @RequestBody VideoDto videoDto) {
        return videoService.mark(videoDto);
    }

    @LoginUser
    @PutMapping("/score")
    @ApiOperation("评分")
    public Boolean score(@Validated(EditValidationGroup.class) @RequestBody VideoDto videoDto) {
        return videoService.score(videoDto);
    }

    @LoginUser
    @GetMapping("/recommend/{id}")
    @ApiOperation("推荐")
    public IPage<VideoVo> recommend(Query query, @PathVariable Long id) {
        return videoService.recommend(query, id);
    }
}
