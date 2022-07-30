package com.pn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pn.entry.Star;
import com.pn.enums.ResponseCode;
import com.pn.mapper.StarMapper;
import com.pn.service.ServicePlus;
import com.pn.service.StarService;
import com.pn.support.Condition;
import com.pn.support.Query;
import com.pn.support.exception.BaseException;
import com.pn.vo.StarVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService, ServicePlus<Star> {

    @Override
    public IPage<StarVo> selectByPage(Query query, Long userId) {
        return this.baseMapper.selectByPage(Condition.getPage(query), userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(Long vid, Long userId) {
        Wrapper<Star> wrapper = new LambdaQueryWrapper<Star>()
                .eq(Star::getVid, vid)
                .eq(Star::getUid, userId);
        if (this.getOne(wrapper, false) != null) {
            throw new BaseException(ResponseCode.SERVICE_ERROR, "已收藏");
        }
        return this.savePlus(new Star().setVid(vid).setUid(userId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean unStart(Long vid, Long userId) {
        Wrapper<Star> wrapper = new LambdaQueryWrapper<Star>()
                .eq(Star::getVid, vid)
                .eq(Star::getUid, userId);
        return this.remove(wrapper);
    }

    @Override
    public Boolean check(Long vid, Long userId) {
        Wrapper<Star> wrapper = new LambdaQueryWrapper<Star>()
                .eq(Star::getVid, vid)
                .eq(Star::getUid, userId);
        return this.getOne(wrapper, false) != null;
    }
}
