package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
