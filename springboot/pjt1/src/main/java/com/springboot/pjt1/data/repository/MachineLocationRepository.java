package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.MachineLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineLocationRepository extends JpaRepository<MachineLocation, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);
}
