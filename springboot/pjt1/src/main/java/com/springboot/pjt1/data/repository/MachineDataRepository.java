package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.MachineData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineDataRepository extends JpaRepository<MachineData, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
