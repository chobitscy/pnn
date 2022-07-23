package com.pn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pn.entry.Follow;
import com.pn.support.Query;
import com.pn.vo.FollowVo;

public interface FollowService extends IService<Follow> {

    /**
     * 关注
     *
     * @param pid    制作人 id
     * @param userId 用户 id
     * @return 是否成功
     */
    Boolean follow(Long pid, Long userId);

    /**
     * 取关
     *
     * @param pid    制作人 id
     * @param userId 用户 id
     * @return 是否成功
     */
    Boolean unFollow(Long pid, Long userId);

    /**
     * 已关注制作人分页
     *
     * @param query  分页参数
     * @param userId 用户 id
     * @return 分页结果
     */
    IPage<FollowVo> selectByPage(Query query, Long userId);
}
