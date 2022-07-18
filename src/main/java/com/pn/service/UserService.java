package com.pn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pn.dto.UserDto;
import com.pn.entry.User;
import com.pn.support.Query;
import com.pn.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService extends IService<User> {

    /**
     * 注册
     *
     * @param userDto 用户对象
     * @param request 请求
     * @return 用户信息 & token
     */
    Map<String, Object> register(UserDto userDto, HttpServletRequest request);

    /**
     * 编辑信息
     *
     * @param userDto 用户对象
     * @param token   token
     * @return 是否成功
     */
    boolean edit(UserDto userDto, String token);

    /**
     * 登录
     *
     * @param userDto 用户对象
     * @return 用户信息 & token
     */
    Map<String, Object> login(UserDto userDto);

    /**
     * 根据 id 获取用户
     *
     * @param id 用户 id
     * @return 用户信息
     */
    UserVo selectById(Long id);

    /**
     * 用户列表分页
     *
     * @param query 分页参数
     * @return 分页结果
     */
    IPage<UserVo> selectByList(Query query);

    /**
     * 关闭用户
     *
     * @param id 用户 id
     * @return 是否成功
     */
    boolean close(Long id);
}
