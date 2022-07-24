package com.pn.controller;

import com.pn.annotation.BaseResponse;
import com.pn.annotation.LoginUser;
import com.pn.entry.Reply;
import com.pn.service.ReplyService;
import com.pn.support.Query;
import com.pn.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 回复接口控制器
 **/
@RestController
@RequestMapping("/reply")
@Api(value = "回复管理", tags = "回复接口")
@BaseResponse
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @LoginUser
    @PostMapping("/add")
    @ApiOperation("添加")
    public Boolean add(@RequestBody Reply reply, HttpServletRequest request) {
        replyService.add(reply, UserUtil.getUserIdByRequest(request), UserUtil.getUserNameByRequest(request));
        return true;
    }

    @LoginUser
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除")
    public Boolean delete(@PathVariable String id) {
        replyService.delete(id);
        return true;
    }

    @LoginUser
    @GetMapping("/page/{vid}")
    @ApiOperation("根据视频 id 获取分页")
    public Page<Reply> selectByPage(Query query, @PathVariable Long vid) {
        return replyService.selectByVid(query, vid);
    }
}
