package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dao.StoreDAO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.entity.Store;
import com.springboot.pjt1.service.StoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreDAO storeDAO;
    private final MemberDAO memberDAO;

    public StoreServiceImpl(StoreDAO storeDAO, MemberDAO memberDAO) {
        this.storeDAO = storeDAO;
        this.memberDAO = memberDAO;
    }


    @Override
    public StoreDTO getStore(long storeSeq) {
        Store store = storeDAO.SelectStoreById(storeSeq);
        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setStoreSeq(store.getStoreSeq());
        storeDTO.setPost(store.getPost());
        storeDTO.setPhoto(store.getPhoto());
        storeDTO.setVideo(store.getVideo());
        storeDTO.setVoice(store.getVoice());
        storeDTO.setCreateSeq(store.getCreateSeq());
        storeDTO.setCreateTime(store.getCreateTime());
        storeDTO.setRecentSeq(store.getRecentSeq());
        storeDTO.setRecentTime(store.getRecentTime());

        return storeDTO;
    }

    @Override
    public StoreDTO insertStore(StoreDTO storeDTO) throws Exception {
        Store store = new Store();

        store.setStoreSeq(storeDTO.getStoreSeq());
        store.setPost(storeDTO.getPost());
        store.setPhoto(storeDTO.getPhoto());
        store.setVideo(storeDTO.getVideo());
        store.setVoice(storeDTO.getVoice());
        store.setCreateSeq(storeDTO.getCreateSeq());
        store.setCreateTime(storeDTO.getCreateTime());
        store.setRecentSeq(storeDTO.getRecentSeq());
        store.setRecentTime(storeDTO.getRecentTime());

        // FK 연결
        Member mem = memberDAO.SelectMemberById(storeDTO.getMemberSeq());
        store.setMember(mem);
        mem.addStore(store);

        Store savedStore = storeDAO.InsertStore(store);
        StoreDTO rStoreDTO = new StoreDTO();

        rStoreDTO.setStoreSeq(savedStore.getStoreSeq());
        rStoreDTO.setPost(savedStore.getPost());
        rStoreDTO.setPhoto(savedStore.getPhoto());
        rStoreDTO.setVideo(savedStore.getVideo());
        rStoreDTO.setVoice(savedStore.getVoice());
        rStoreDTO.setCreateSeq(savedStore.getCreateSeq());
        rStoreDTO.setCreateTime(savedStore.getCreateTime());
        rStoreDTO.setRecentSeq(savedStore.getRecentSeq());
        rStoreDTO.setRecentTime(savedStore.getRecentTime());
        rStoreDTO.setMemberSeq(mem.getMemberSeq());

        return rStoreDTO;
    }


    @Override
    public StoreDTO updateStore(long storeSeq, String photo, String video, String post, String voice, long recentSeq) throws Exception {
        Store updatedStore = storeDAO.UpdateStoreById(storeSeq,photo,video,post, voice, recentSeq);
        StoreDTO rStoreDTO = new StoreDTO();

        rStoreDTO.setStoreSeq(updatedStore.getStoreSeq());
        rStoreDTO.setPost(updatedStore.getPost());
        rStoreDTO.setPhoto(updatedStore.getPhoto());
        rStoreDTO.setVideo(updatedStore.getVideo());
        rStoreDTO.setVoice(updatedStore.getVoice());
        rStoreDTO.setCreateSeq(updatedStore.getCreateSeq());
        rStoreDTO.setCreateTime(updatedStore.getCreateTime());
        rStoreDTO.setRecentSeq(updatedStore.getRecentSeq());
        rStoreDTO.setRecentTime(updatedStore.getRecentTime());

        return rStoreDTO;
    }

    @Override
    public void deleteStore(long storeSeq) throws Exception {
        storeDAO.DeleteStoreById(storeSeq);
    }
}
