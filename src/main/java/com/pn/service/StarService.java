package com.pn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pn.entry.Star;
import com.pn.support.Query;
import com.pn.vo.StarVo;

public interface StarService extends IService<Star> {

    /**
     * 分页
     *
     * @param query  分页参数
     * @param userId 用户 id
     * @return 分页结果
     */
    IPage<StarVo> selectByPage(Query query, Long userId);

    /**
     * 收藏
     *
     * @param vid    视频 id
     * @param userId 用户 id
     * @return 是否成功
     */
    Boolean start(Long vid, Long userId);

    /**
     * 取消收藏
     *
     * @param id 收藏 id
     * @return 是否成功
     */
    Boolean unStart(Long id);

    /**
     * 检查
     *
     * @param vid    视频 id
     * @param userId 用户 id
     * @return 是否成功
     */
    Boolean check(Long vid, Long userId);
}
