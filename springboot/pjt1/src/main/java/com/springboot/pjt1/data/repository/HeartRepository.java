package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.dto.HeartDTO;
import com.springboot.pjt1.data.entity.Comment;
import com.springboot.pjt1.data.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByFeedSeqAndMemberSeq(long feedSeq, long memberSeq);
    List<Heart> findAllByMemberSeq(long memberSeq);
    HeartDTO findAllByFeedSeq(long feedSeq);
}

