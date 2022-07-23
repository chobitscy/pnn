package com.pn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.entry.Follow;
import com.pn.vo.FollowVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

    /**
     * 分页
     *
     * @param page 分页参数
     * @param uid  用户 id
     * @return 分页结果
     */
    IPage<FollowVo> selectByPage(IPage<Follow> page, @Param("uid") Long uid);
}
