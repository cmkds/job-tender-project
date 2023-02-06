package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.StoreDAO;
import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.entity.Store;
import com.springboot.pjt1.data.repository.MemberRepository;
import com.springboot.pjt1.data.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Component
public class StoreDAOImpl implements StoreDAO {
    private StoreRepository storeRepository;
    private MemberRepository memberRepository; ///

    @Autowired
    public StoreDAOImpl(StoreRepository storeRepository, MemberRepository memberRepository){
        this.storeRepository = storeRepository;
        this.memberRepository = memberRepository;
    }
    
    @Override
    public Store InsertStore(Store store) throws Exception {
        return storeRepository.save(store);
    }


    @Override
    public Store SelectStoreById(long storeSeq) {
        Store selectedStore = storeRepository.getById(storeSeq);

        return selectedStore;
    }

    @Override
    public Store UpdateStoreById(long storeSeq, String photo, String video,
                                 String post, String voice) throws Exception {
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

    @Override
    public List<StoreDTO> SelectStoreByMemberSeq(long memberSeq) {
        List<Store> stores = storeRepository.findByMemberSeq(memberSeq);

        List<StoreDTO> storeDTOs = new ArrayList<>();

        for (int i = 0; i < stores.size(); i++) {
            StoreDTO storeDTO = new StoreDTO();

            storeDTO.setMemberSeq(stores.get(i).getStoreSeq());
            storeDTO.setStoreSeq(stores.get(i).getStoreSeq());
            storeDTO.setVoice(stores.get(i).getVoice());
            storeDTO.setPost(stores.get(i).getPost());
            storeDTO.setVoice(stores.get(i).getVoice());
            storeDTO.setRecentTime(stores.get(i).getRecentTime());
            storeDTO.setCreateTime(stores.get(i).getCreateTime());
            storeDTO.setPhoto(stores.get(i).getPhoto());

            storeDTOs.add(storeDTO);
        }

        return storeDTOs;
    }

    @Override
    public void DeleteStoreByMemberSeq(long memberSeq) {
        List<Store> stores = storeRepository.findByMemberSeq(memberSeq);

        for(Store store : stores)
            storeRepository.delete(store);
    }
}
