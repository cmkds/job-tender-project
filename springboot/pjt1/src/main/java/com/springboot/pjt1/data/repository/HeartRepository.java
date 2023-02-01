package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Comment;
import com.springboot.pjt1.data.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}

