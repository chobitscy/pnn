package com.pn.wrapper;

import cn.hutool.extra.cglib.CglibUtil;
import com.pn.entry.User;
import com.pn.support.BaseEntityWrapper;
import com.pn.vo.UserVo;
import org.springframework.beans.BeanUtils;

/**
 * 用户转换类
 **/
public class UserWrapper extends BaseEntityWrapper<User, UserVo> {

    public static UserWrapper build() {
        return new UserWrapper();
    }

    @Override
    public UserVo entityVO(User entity) {
        if (entity == null) {
            return null;
        }
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}