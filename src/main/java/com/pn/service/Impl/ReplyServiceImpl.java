package com.pn.service.Impl;

import com.pn.entry.Reply;
import com.pn.repository.ReplyRepository;
import com.pn.service.ReplyService;
import com.pn.support.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public void push(Reply reply, Long userId, String name) {
        Reply temp = new Reply()
                .setContent(reply.getContent())
                .setDate(new Date())
                .setUid(userId)
                .setUsername(name)
                .setVid(reply.getVid());
        replyRepository.insert(temp);
    }

    @Override
    public void delete(String id) {
        replyRepository.deleteById(id);
    }

    @Override
    public Page<Reply> selectByVid(Query query, Long vid) {
        int page = query.getCurrent() == null ? 0 : query.getCurrent();
        int size = query.getSize() == null ? 10 : query.getSize();
        Pageable pageable = PageRequest.of(page, size);
        return replyRepository.findReplyByVid(vid, pageable);
    }
}
