package com.pn.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pn.entry.BaseEntity;

import java.util.Date;

public interface ServicePlus<T extends BaseEntity> extends IService<T> {

    default boolean savePlus(T entry) {
        Date now = new Date();
        entry.setCreateTime(now);
        entry.setUpdateTime(now);
        entry.setState(1);
        return this.save(entry);
    }

    default boolean updatePlus(UpdateWrapper<T> wrapper) {
        Date now = new Date();
        wrapper.set("update_time", now);
        return this.update(wrapper);
    }
}
