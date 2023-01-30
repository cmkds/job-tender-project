package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);

}
