package com.pn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pn.dto.VideoDto;
import com.pn.entry.Video;
import com.pn.support.Query;
import com.pn.vo.VideoVo;

public interface VideoService extends IService<Video> {

    /**
     * 流行
     *
     * @param query 分页参数
     * @param day   周期
     * @return 分页结果
     */
    IPage<VideoVo> popular(Query query, Integer day);

    /**
     * 分页
     *
     * @param query 分页参数
     * @return 分页结果
     */
    IPage<VideoVo> selectByPage(Query query);

    /**
     * 搜索
     *
     * @param query 分页参数
     * @param vid   视频 id
     * @return 分页结果
     */
    IPage<VideoVo> search(Query query, String vid);

    /**
     * 详情
     *
     * @param id id
     * @return 视频视图类
     */
    VideoVo detail(Long id);

    /**
     * 标记
     *
     * @param videoDto 视频传输类
     * @return 是否成功
     */
    Boolean mark(VideoDto videoDto);

    /**
     * 评分
     *
     * @param videoDto 视频传输类
     * @return 是否成功
     */
    Boolean score(VideoDto videoDto);

    /**
     * 推荐
     *
     * @param query 分页参数
     * @param id    id
     * @return 分页结果
     */
    IPage<VideoVo> recommend(Query query, Long id);

    /**
     * 当前用户关注制作人的作品
     *
     * @param query  分页参数
     * @param userId 用户 id
     * @return 分页结果
     */
    IPage<VideoVo> follow(Query query, Long userId);
}