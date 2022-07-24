package com.pn.repository;

import com.pn.entry.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReplyRepository extends MongoRepository<Reply, String> {

    Page<Reply> findReplyByVid(Long vid, Pageable pageable);
}
