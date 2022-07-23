package com.pn.wrapper;

import com.pn.entry.Follow;
import com.pn.support.BaseEntityWrapper;
import com.pn.vo.FollowVo;
import org.springframework.beans.BeanUtils;

/**
 * 关注转化类
 */
public class FollowWrapper extends BaseEntityWrapper<Follow, FollowVo> {

    public static FollowWrapper build() {
        return new FollowWrapper();
    }

    @Override
    public FollowVo entityVO(Follow entity) {
        if (entity == null) {
            return null;
        }
        FollowVo vo = new FollowVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
