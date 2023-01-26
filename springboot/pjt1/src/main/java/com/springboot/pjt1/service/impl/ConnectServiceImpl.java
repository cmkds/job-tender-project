package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.ConnectDAO;
import com.springboot.pjt1.data.dto.ConnectDTO;
import com.springboot.pjt1.data.entity.Connect;
import com.springboot.pjt1.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectServiceImpl implements ConnectService {
    private final ConnectDAO connectDAO;

    @Autowired
    public ConnectServiceImpl(ConnectDAO connectDAO) {
        this.connectDAO = connectDAO;
    }

    @Override
    public ConnectDTO getConnect(long connectSeq) {
        Connect connect = connectDAO.SelectConnectById(connectSeq);
        ConnectDTO connectDTO = new ConnectDTO();

        connectDTO.setConnectSeq(connect.getConnectSeq());
        connectDTO.setFollower(connect.getFollower());
        connectDTO.setFollowing(connect.getFollowing());
        connectDTO.setCreateTime(connect.getCreateTime());

        return connectDTO;
    }

    @Override
    public ConnectDTO insertConnect(ConnectDTO connectDTO) throws Exception {
        Connect connect = new Connect();

        connect.setConnectSeq(connectDTO.getConnectSeq());
        connect.setFollower(connectDTO.getFollower());
        connect.setFollowing(connectDTO.getFollowing());
        connect.setCreateTime(connectDTO.getCreateTime());

        Connect savedConnect = connectDAO.InsertConnect(connect);
        ConnectDTO rConnectDTO = new ConnectDTO();

        rConnectDTO.setConnectSeq(savedConnect.getConnectSeq());
        rConnectDTO.setFollower(savedConnect.getFollower());
        rConnectDTO.setFollowing(savedConnect.getFollowing());
        rConnectDTO.setCreateTime(savedConnect.getCreateTime());

        return rConnectDTO;
    }

    @Override
    public ConnectDTO updateConnect(long connectSeq, long followerSeq, long followingSeq) throws Exception {
        Connect updatedConnect = connectDAO.UpdateConnectById(connectSeq,followerSeq, followingSeq);
        ConnectDTO rConnectDTO = new ConnectDTO();

        rConnectDTO.setConnectSeq(updatedConnect.getConnectSeq());
        rConnectDTO.setFollower(updatedConnect.getFollower());
        rConnectDTO.setFollowing(updatedConnect.getFollowing());
        rConnectDTO.setCreateTime(updatedConnect.getCreateTime());

        return rConnectDTO;
    }

    @Override
    public void deleteConnect(long connectSeq) throws Exception {
        connectDAO.DeleteConnectById(connectSeq);
    }
}
