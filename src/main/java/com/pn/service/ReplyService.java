package com.pn.service;

import com.pn.entry.Reply;
import com.pn.support.Query;
import org.springframework.data.domain.Page;

public interface ReplyService {

    /**
     * 回复
     *
     * @param reply  回复实体类
     * @param userId 用户 id
     * @param name   用户名
     * @return 是否成功
     */
    void push(Reply reply, Long userId, String name);

    /**
     * 删除回复
     *
     * @param id 回复 id
     * @return 是否成功
     */
    void delete(String id);

    /**
     * 根据视频 id 获取回复分页
     *
     * @param query 分页参数
     * @param vid   视频 id
     * @return 分页结果
     */
    Page<Reply> selectByVid(Query query, Long vid);
}
