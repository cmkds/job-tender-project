package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
