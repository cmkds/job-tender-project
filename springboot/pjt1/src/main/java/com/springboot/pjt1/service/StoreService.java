package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.StoreDTO;
import org.springframework.stereotype.Service;

public interface StoreService {
    StoreDTO getStore(long storeSeq);
    StoreDTO insertStore(StoreDTO storeDTO) throws Exception;
    StoreDTO updateStore(long storeSeq, String photo, String video,
                         String post, String voice, long recentSeq)throws Exception;
    void deleteStore(long storeSeq)throws Exception;
}