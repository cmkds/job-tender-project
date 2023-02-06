package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByMemberSeq(long memberSeq);
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
