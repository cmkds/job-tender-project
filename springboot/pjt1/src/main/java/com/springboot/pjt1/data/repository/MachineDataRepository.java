package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.dto.MachineDataDTO;
import com.springboot.pjt1.data.entity.MachineData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineDataRepository extends JpaRepository<MachineData, Long> {
    List<MachineData> findAllByOrderByCreateTimeDesc();
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
