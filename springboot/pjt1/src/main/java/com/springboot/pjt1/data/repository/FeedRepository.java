package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
