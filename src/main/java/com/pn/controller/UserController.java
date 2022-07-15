package com.pn.controller;

import com.pn.dto.UserDto;
import com.pn.service.UserService;
import com.pn.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description 用户接口控制器
 * @Author chobit
 * @Data 2022/7/14 17:56
 **/
@RestController
@RequestMapping(value = "/user")
@Api(value = "用户管理", tags = "用户接口")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册")
    public ResponseEntity<Map<String, Object>> register(@Validated @RequestBody UserDto userDto,
                                                        HttpServletRequest request) {
        return ResponseEntity.ok(userService.register(userDto, request));
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public ResponseEntity<Map<String, Object>> login(@Validated @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.login(userDto));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据 id 获取用户信息")
    public ResponseEntity<UserVo> selectById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectById(id));
    }
}
