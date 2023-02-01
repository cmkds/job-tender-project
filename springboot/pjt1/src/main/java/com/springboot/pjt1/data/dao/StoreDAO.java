package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Store;

public interface StoreDAO {
    Store InsertStore(Store store) throws Exception;
    Store SelectStoreById(long storeSeq);
    Store UpdateStoreById(long storeSeq, String photo, String video,
                          String post, String voice) throws Exception;
    void DeleteStoreById(long storeSeq) throws Exception;
}
