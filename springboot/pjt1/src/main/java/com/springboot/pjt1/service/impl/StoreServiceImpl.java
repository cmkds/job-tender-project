package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dao.StoreDAO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.dto.custom.StoreInputDTO;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.entity.Store;
import com.springboot.pjt1.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        storeDTO.setCreateTime(store.getCreateTime());
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
        store.setCreateTime(storeDTO.getCreateTime());
        store.setRecentTime(storeDTO.getRecentTime());

        Store savedStore = storeDAO.InsertStore(store);
        StoreDTO rStoreDTO = new StoreDTO();

        rStoreDTO.setStoreSeq(savedStore.getStoreSeq());
        rStoreDTO.setPost(savedStore.getPost());
        rStoreDTO.setPhoto(savedStore.getPhoto());
        rStoreDTO.setVideo(savedStore.getVideo());
        rStoreDTO.setVoice(savedStore.getVoice());
        rStoreDTO.setCreateTime(savedStore.getCreateTime());
        rStoreDTO.setRecentTime(savedStore.getRecentTime());

        return rStoreDTO;
    }

    @Override
    public StoreDTO insertStore(StoreInputDTO storeInputDTO) throws Exception {
        Store store = new Store();

        store.setStoreSeq(storeInputDTO.getStoreSeq());
        store.setPost(storeInputDTO.getPost());
        store.setPhoto(storeInputDTO.getPhoto());
        store.setVideo(storeInputDTO.getVideo());
        //store.setVoice(storeInputDTO.getVoice());
        store.setCreateTime(new Date());
        store.setRecentTime(new Date());

        Store savedStore = storeDAO.InsertStore(store);
        StoreDTO rStoreDTO = new StoreDTO();

        rStoreDTO.setStoreSeq(savedStore.getStoreSeq());
        rStoreDTO.setPost(savedStore.getPost());
        rStoreDTO.setPhoto(savedStore.getPhoto());
        rStoreDTO.setVideo(savedStore.getVideo());
        rStoreDTO.setVoice(savedStore.getVoice());
        rStoreDTO.setCreateTime(savedStore.getCreateTime());
        rStoreDTO.setRecentTime(savedStore.getRecentTime());

        return rStoreDTO;
    }


    @Override
    public StoreDTO updateStore(long storeSeq, String photo, String video, String post, String voice, long recentSeq) throws Exception {
        Store updatedStore = storeDAO.UpdateStoreById(storeSeq,photo,video,post, voice);
        StoreDTO rStoreDTO = new StoreDTO();

        rStoreDTO.setStoreSeq(updatedStore.getStoreSeq());
        rStoreDTO.setPost(updatedStore.getPost());
        rStoreDTO.setPhoto(updatedStore.getPhoto());
        rStoreDTO.setVideo(updatedStore.getVideo());
        rStoreDTO.setVoice(updatedStore.getVoice());
        rStoreDTO.setCreateTime(updatedStore.getCreateTime());
        rStoreDTO.setRecentTime(updatedStore.getRecentTime());

        return rStoreDTO;
    }

    @Override
    public void deleteStore(long storeSeq) throws Exception {
        storeDAO.DeleteStoreById(storeSeq);
    }

    @Override
    public List<StoreDTO> getStoreByMemberSeq(long memberSeq) {
        return storeDAO.SelectStoreByMemberSeq(memberSeq);
    }

    @Override
    public void deleteStoreByMemberSeq(long memberSeq) {
        storeDAO.DeleteStoreByMemberSeq(memberSeq);
    }

    @Override
    public StoreDTO getStoreByMachineDataSeq(long machineDataSeq) {
        Store store = storeDAO.SelectStoreByMachineDataSeq(machineDataSeq);
        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setStoreSeq(store.getStoreSeq());
        storeDTO.setPost(store.getPost());
        storeDTO.setPhoto(store.getPhoto());
        storeDTO.setVideo(store.getVideo());
        storeDTO.setVoice(store.getVoice());
        storeDTO.setCreateTime(store.getCreateTime());
        storeDTO.setRecentTime(store.getRecentTime());

        return storeDTO;
    }
}
