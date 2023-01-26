package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}

