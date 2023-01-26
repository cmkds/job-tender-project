package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Connect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectRepository extends JpaRepository<Connect, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
