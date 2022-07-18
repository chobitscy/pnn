package com.pn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pn.dto.UserDto;
import com.pn.entry.BaseEntity;
import com.pn.entry.User;
import com.pn.enums.ResponseCode;
import com.pn.mapper.UserMapper;
import com.pn.service.ServicePlus;
import com.pn.service.UserService;
import com.pn.support.exception.BaseException;
import com.pn.support.Condition;
import com.pn.support.Query;
import com.pn.utils.JwtUtils;
import com.pn.utils.NetUtil;
import com.pn.vo.UserVo;
import com.pn.wrapper.UserWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务实现类
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, ServicePlus<User> {

    @Value("${user.register:}")
    private Boolean register;

    @Override
    public Map<String, Object> register(UserDto userDto, HttpServletRequest request) {
        if (!register) {
            throw new BaseException(ResponseCode.SERVICE_ERROR, "关闭注册");
        }
        userDto.setPassword(DigestUtils.md5Hex(userDto.getPassword()));
        userDto.setIp(NetUtil.getIpAddr(request));
        boolean save = this.savePlus(userDto);
        if (!save) {
            throw new BaseException(ResponseCode.SERVICE_ERROR, "注册失败");
        } else {
            return buildUserInfo(userDto);
        }
    }

    @Override
    public boolean edit(UserDto userDto, String token) {
        String name = userDto.getName();
        String avatar = userDto.getAvatar();
        Long id = JwtUtils.getUserId(token);
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .set(name != null, "name", name)
                .set(avatar != null, "avatar", avatar)
                .eq("id", id)
                .eq("state", 1);
        return this.updatePlus(wrapper);
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
                        .eq(User::getPassword, decodePW))
                .eq(BaseEntity::getState, 1);
        User target = this.getOne(wrapper, false);
        if (target == null) {
            throw new BaseException(ResponseCode.SERVICE_ERROR, "账户或密码错误");
        } else {
            BeanUtils.copyProperties(target, userDto);
            return buildUserInfo(userDto);
        }
    }

    @Override
    public UserVo selectById(Long id) {
        Wrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(BaseEntity::getId, id)
                .eq(BaseEntity::getState, 1);
        return UserWrapper.build().entityVO(this.getOne(wrapper, false));
    }

    @Override
    public IPage<UserVo> selectByList(Query query) {
        IPage<User> page = Condition.getPage(query);
        return UserWrapper.build().pageVO(this.page(page, new LambdaQueryWrapper<User>()
                .eq(BaseEntity::getState, 1)));
    }

    @Override
    public boolean close(Long id) {
        return this.update(new LambdaUpdateWrapper<User>()
                .set(BaseEntity::getState, 0)
                .eq(BaseEntity::getId, id));
    }

    @Override
    public UserVo search(String keyword) {
        Wrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getName,keyword)
                .or()
                .eq(User::getEmail,keyword);
        return UserWrapper.build().entityVO(this.getOne(wrapper, false));
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