package com.pn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pn.entry.Star;
import com.pn.mapper.StarMapper;
import com.pn.service.ServicePlus;
import com.pn.service.StarService;
import com.pn.support.Condition;
import com.pn.support.Query;
import com.pn.vo.StarVo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService, ServicePlus<Star> {

    @Override
    @Cacheable(value = "star", keyGenerator = "md5KeyGenerator")
    public IPage<StarVo> selectByPage(Query query, Long userId) {
        return this.baseMapper.selectByPage(Condition.getPage(query), userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean start(Long vid, Long userId) {
        return this.savePlus(new Star().setVid(vid).setUid(userId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean unStart(Long id) {
        return this.removeById(id);
    }

    @Override
    public Boolean check(Long vid, Long userId) {
        Wrapper<Star> wrapper = new LambdaQueryWrapper<Star>()
                .eq(Star::getVid, vid)
                .eq(Star::getUid, userId);
        return this.getOne(wrapper, false) != null;
    }
}
