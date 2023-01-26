package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.StoreDAO;
import com.springboot.pjt1.data.entity.Store;
import com.springboot.pjt1.data.entity.Store;
import com.springboot.pjt1.data.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class StoreDAOImpl implements StoreDAO {
    private StoreRepository storeRepository;

    @Autowired
    public StoreDAOImpl(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }
    
    @Override
    public Store InsertStore(Store store) throws Exception {
        Store savedStore = storeRepository.save(store);

        return savedStore;
    }

    @Override
    public Store SelectStoreById(long storeSeq) {
        Store selectedStore = storeRepository.getById(storeSeq);

        return selectedStore;
    }

    @Override
    public Store UpdateStoreById(long storeSeq, String photo, String video,
                                 String post, String voice, long recentSeq) throws Exception {
        // get data using ID
        Optional<Store> selectedStore = storeRepository.findById(storeSeq);
        Store updatedStore;

        if(selectedStore.isPresent()){
            Store store = selectedStore.get();

            store.setPhoto(photo);
            store.setVideo(video);
            store.setPost(post);
            store.setVoice(voice);

            store.setRecentTime(new Date());
            store.setRecentSeq(recentSeq);

            updatedStore = storeRepository.save(store);
        }

        else
            throw new Exception();

        return updatedStore;
    }

    @Override
    public void DeleteStoreById(long storeSeq) throws Exception {
        Optional<Store> selectedStore = storeRepository.findById(storeSeq);

        if (selectedStore.isPresent()){
            Store Store = selectedStore.get();
            storeRepository.delete(Store);
        }

        else
            throw new Exception();
    }
}
