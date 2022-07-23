package com.pn.service.Impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pn.dto.VideoDto;
import com.pn.entry.Follow;
import com.pn.entry.Video;
import com.pn.enums.ResponseCode;
import com.pn.mapper.VideoMapper;
import com.pn.service.FollowService;
import com.pn.service.ServicePlus;
import com.pn.service.VideoService;
import com.pn.support.Condition;
import com.pn.support.Query;
import com.pn.support.exception.BaseException;
import com.pn.vo.VideoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 视频服务实现类
 **/
@Service
@RequiredArgsConstructor
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService, ServicePlus<Video> {

    private final FollowService followService;

    @Override
    @Cacheable(value = "video", keyGenerator = "md5KeyGenerator")
    public IPage<VideoVo> popular(Query query, Integer day) {
        Date now = new Date();
        Date begin = DateUtil.offsetDay(now, -day);
        IPage<Video> page = Condition.getPage(query);
        Wrapper<Video> wrapper = new LambdaQueryWrapper<Video>()
                .isNotNull(Video::getCreateDate)
                .between(Video::getPubDate, begin, now)
                .orderByDesc(Video::getRate);
        return this.baseMapper.selectByPage(page, wrapper);
    }

    @Override
    @Cacheable(value = "video", keyGenerator = "md5KeyGenerator")
    public IPage<VideoVo> selectByPage(Query query) {
        IPage<Video> page = Condition.getPage(query);
        return this.baseMapper.selectByPage(page, new LambdaQueryWrapper<Video>()
                .orderByDesc(Video::getRate));
    }

    @Override
    @Cacheable(value = "video", keyGenerator = "md5KeyGenerator")
    public IPage<VideoVo> search(Query query, String vid) {
        IPage<Video> page = Condition.getPage(query);
        return this.baseMapper.selectByPage(page, new LambdaQueryWrapper<Video>()
                .like(vid != null, Video::getVid, vid)
                .orderByDesc(Video::getRate));
    }

    @Override
    @Cacheable(value = "video", keyGenerator = "md5KeyGenerator")
    public VideoVo detail(Long id) {
        List<VideoVo> videoVoList = this.baseMapper.selectByOne(new QueryWrapper<Video>()
                .eq(id != null, "a.id", id));
        videoVoList = videoVoList.isEmpty() ? null : videoVoList;
        return Optional.ofNullable(videoVoList)
                .orElseThrow(() -> new BaseException(ResponseCode.SERVICE_ERROR, "video not found"))
                .get(0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean mark(VideoDto videoDto) {
        return this.updatePlus(new UpdateWrapper<Video>()
                .set("uncensored", videoDto.getUncensored())
                .eq("id", videoDto.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean score(VideoDto videoDto) {
        VideoVo target = this.detail(videoDto.getId());
        Float score = BigDecimal.valueOf(target.getRate())
                .add(BigDecimal.valueOf(videoDto.getScore()))
                .divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP).floatValue();
        return this.updatePlus(new UpdateWrapper<Video>()
                .set("rate", score)
                .eq("id", videoDto.getId()));
    }

    @Override
    @Cacheable(value = "video", keyGenerator = "md5KeyGenerator")
    public IPage<VideoVo> recommend(Query query, Long id) {
        IPage<Video> page = Condition.getPage(query);
        VideoVo target = this.detail(id);
        String exp = target.getTid().replace(",", "|");
        return this.baseMapper.recommend(page, id, target.getPid(), exp);
    }

    @Override
    @Cacheable(value = "video", keyGenerator = "md5KeyGenerator")
    public IPage<VideoVo> follow(Query query, Long userId) {
        List<Long> followProductList = followService.list(new LambdaQueryWrapper<Follow>()
                .eq(Follow::getUid, userId))
                .stream()
                .map(Follow::getPid)
                .collect(Collectors.toList());
        Wrapper<Video> wrapper = new LambdaQueryWrapper<Video>()
                .in(Video::getPid, followProductList)
                .orderByDesc(Video::getCreateDate);
        return this.baseMapper.selectByPage(Condition.getPage(query), wrapper);
    }
}
