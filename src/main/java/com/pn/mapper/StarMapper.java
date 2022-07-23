package com.pn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.entry.Star;
import com.pn.vo.StarVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StarMapper extends BaseMapper<Star> {

    /**
     * 分页
     *
     * @param page   分页参数
     * @param userId 用户 id
     * @return 分页结果
     */
    IPage<StarVo> selectByPage(IPage<Star> page, @Param("userId") Long userId);
}
