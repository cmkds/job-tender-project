package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByMemberSeq(long memberSeq);
    Store findByMachineDataSeq(long machineDataSeq);
}