package com.pn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pn.entry.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
