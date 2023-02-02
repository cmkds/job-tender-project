package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Comment;
import com.springboot.pjt1.data.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByFeedAndMember(long feedSeq, long memberSeq);
}

