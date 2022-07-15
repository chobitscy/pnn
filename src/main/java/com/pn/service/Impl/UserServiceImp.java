package com.pn.service.Impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pn.dto.UserDto;
import com.pn.entry.User;
import com.pn.mapper.UserMapper;
import com.pn.service.ServicePlus;
import com.pn.service.UserService;
import com.pn.support.BusinessException;
import com.pn.utils.JwtUtils;
import com.pn.utils.NetUtil;
import com.pn.vo.UserVo;
import com.pn.wrapper.UserWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 用户服务实现类
 * @Author chobit
 * @Data 2022/7/14 17:51
 **/
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService, ServicePlus<User> {

    @Value("${user.register:}")
    private Boolean register;

    @Override
    public Map<String, Object> register(UserDto userDto, HttpServletRequest request) {
        if (!register) {
            throw new BusinessException("关闭注册");
        }
        userDto.setPassword(DigestUtils.md5Hex(userDto.getPassword()));
        userDto.setIp(NetUtil.getIpAddr(request));
        boolean save = this.savePlus(userDto);
        if (!save) {
            throw new BusinessException("注册失败");
        } else {
            return buildUserInfo(userDto);
        }
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public boolean edit(UserDto userDto) {
        return false;
    }

    @Override
    public Map<String, Object> login(UserDto userDto) {
        String decodePW = DigestUtils.md5Hex(userDto.getPassword());
        Wrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .and(userDto.getEmail() != null, innerWrapper -> innerWrapper
                        .eq(User::getEmail, userDto.getEmail())
                        .eq(User::getPassword, decodePW))
                .or(userDto.getName() != null, innerWrapper -> innerWrapper
                        .eq(User::getName, userDto.getName())
                        .eq(User::getPassword, decodePW));
        User target = this.getOne(wrapper, false);
        if (target == null) {
            throw new BusinessException("账户或密码错误");
        } else {
            CglibUtil.copy(target, userDto);
            return buildUserInfo(userDto);
        }
    }

    @Override
    public UserVo selectById(Long id) {
        return UserWrapper.build().entityVO(this.getById(id));
    }

    /**
     * 生成用户登录信息
     *
     * @param userDto 用户类
     * @return 用户信息 & token
     */
    private Map<String, Object> buildUserInfo(UserDto userDto) {
        String token = JwtUtils.createToken(userDto, userDto.getExpire());
        Map<String, Object> map = new HashMap<>();
        map.put("user", new UserWrapper().entityVO(userDto));
        map.put("token", token);
        return map;
    }
}
