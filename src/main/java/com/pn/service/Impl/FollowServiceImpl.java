package com.pn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pn.entry.Follow;
import com.pn.enums.ResponseCode;
import com.pn.mapper.FollowMapper;
import com.pn.service.FollowService;
import com.pn.service.ServicePlus;
import com.pn.support.Condition;
import com.pn.support.Query;
import com.pn.support.exception.BaseException;
import com.pn.vo.FollowVo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService, ServicePlus<Follow> {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean follow(Long pid, Long userId) {
        Wrapper<Follow> wrapper = new LambdaQueryWrapper<Follow>()
                .eq(Follow::getPid, pid)
                .eq(Follow::getUid, userId);
        if (this.getOne(wrapper, false) != null) {
            throw new BaseException(ResponseCode.SERVICE_ERROR, "已关注");
        }
        return this.savePlus(new Follow().setUid(userId).setPid(pid));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean unFollow(Long pid, Long userId) {
        Wrapper<Follow> wrapper = new LambdaQueryWrapper<Follow>()
                .eq(Follow::getUid, userId)
                .eq(Follow::getPid, pid);
        return this.remove(wrapper);
    }

    @Override
    public IPage<FollowVo> selectByPage(Query query, Long userId) {
        return this.baseMapper.selectByPage(Condition.getPage(query), userId);
    }
}
