package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Store;
import com.springboot.pjt1.data.entity.UploadFile;
import com.springboot.pjt1.service.S3Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
    //void save(UploadFile uploadFile);

}
