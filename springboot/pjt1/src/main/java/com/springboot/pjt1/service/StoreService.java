package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.dto.custom.StoreInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StoreService {
    StoreDTO getStore(long storeSeq);
    StoreDTO insertStore(StoreDTO storeDTO) throws Exception;
    StoreDTO insertStore(StoreInputDTO storeInputDTO) throws Exception;
    StoreDTO updateStore(long storeSeq, String photo, String video,
                         String post, String voice, long recentSeq)throws Exception;
    void deleteStore(long storeSeq)throws Exception;
    List<StoreDTO> getStoreByMemberSeq(long memberSeq);
    void deleteStoreByMemberSeq(long memberSeq);
    List<StoreDTO> getStoreByMemberSeqDesc(long memberSeq);

    List<StoreDTO> getStoreByStoreSeqDesc(long storeSeq);
}
