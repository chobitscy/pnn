package com.pn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pn.dto.UserDto;
import com.pn.entry.User;
import com.pn.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService extends IService<User> {

    Map<String, Object> register(UserDto userDto, HttpServletRequest request);

    boolean remove(Long id);

    boolean edit(UserDto userDto);

    Map<String, Object> login(UserDto userDto);

    UserVo selectById(Long id);

}
