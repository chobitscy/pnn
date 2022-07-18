package com.pn.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pn.entry.Video;
import com.pn.vo.VideoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 分页
     *
     * @param page    分页参数
     * @param wrapper 条件
     * @return 分页结构
     */
    IPage<VideoVo> selectByPage(IPage<Video> page, @Param(Constants.WRAPPER) Wrapper<Video> wrapper);

    /**
     * 查询
     *
     * @param wrapper 条件
     * @return 分页结构
     */
    List<VideoVo> selectByOne(@Param(Constants.WRAPPER) Wrapper<Video> wrapper);

    /**
     * 推荐
     *
     * @param page 分页参数
     * @param pid  产品 id
     * @param exp  正则表达式
     * @return 分页结果
     */
    IPage<VideoVo> recommend(IPage<Video> page, @Param("id") Long id, @Param("pid") Long pid, @Param("exp") String exp);
}
