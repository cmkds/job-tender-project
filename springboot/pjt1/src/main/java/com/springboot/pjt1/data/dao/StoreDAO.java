package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.entity.Store;

import java.util.List;

public interface StoreDAO {
    Store InsertStore(Store store) throws Exception;
    Store SelectStoreById(long storeSeq);
    Store UpdateStoreById(long storeSeq, String photo, String video,
                          String post, String voice) throws Exception;
    void DeleteStoreById(long storeSeq) throws Exception;

    List<StoreDTO> SelectStoreByMemberSeq(long memberSeq);

    void DeleteStoreByMemberSeq(long memberSeq);
}
