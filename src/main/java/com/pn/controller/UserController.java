package com.pn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.annotation.Admin;
import com.pn.annotation.BaseResponse;
import com.pn.annotation.LoginUser;
import com.pn.dto.UserDto;
import com.pn.service.UserService;
import com.pn.support.Query;
import com.pn.support.validate.ExpireValidationGroup;
import com.pn.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户接口控制器
 **/
@RestController
@RequestMapping(value = "/user")
@Api(value = "用户管理", tags = "用户接口")
@RequiredArgsConstructor
@BaseResponse
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册")
    public Map<String, Object> register(@Validated(value = {ExpireValidationGroup.class}) @RequestBody UserDto userDto,
                                        HttpServletRequest request) {
        return userService.register(userDto, request);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public Map<String, Object> login(@Validated(value = {ExpireValidationGroup.class}) @RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    @Admin
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据 id 获取用户信息")
    public UserVo selectById(@PathVariable Long id) {
        return userService.selectById(id);
    }

    @LoginUser
    @PutMapping(value = "/")
    @ApiOperation(value = "修改用户信息")
    public Boolean edit(@Validated @RequestBody UserDto userDto, HttpServletRequest request) {
        return userService.edit(userDto, request.getHeader("Authorization"));
    }

    @Admin
    @GetMapping(value = "/page")
    @ApiOperation(value = "用户列表")
    public IPage<UserVo> selectByList(Query query) {
        return userService.selectByList(query);
    }

    @Admin
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "关闭用户")
    public Boolean close(@PathVariable Long id) {
        return userService.close(id);
    }

    @Admin
    @GetMapping(value = "/search/{keyword}")
    @ApiOperation(value = "搜索")
    public UserVo search(@PathVariable String keyword) {
        return userService.search(keyword);
    }
}