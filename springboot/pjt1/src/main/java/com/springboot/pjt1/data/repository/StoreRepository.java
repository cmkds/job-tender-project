package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    //Optional<Comment> findByCommentSeq(long commentSeq);

}