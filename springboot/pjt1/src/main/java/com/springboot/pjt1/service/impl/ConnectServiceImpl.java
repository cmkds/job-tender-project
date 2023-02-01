package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.ConnectDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.ConnectDTO;
import com.springboot.pjt1.data.entity.Connect;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectServiceImpl implements ConnectService {
    private final ConnectDAO connectDAO;
    private final MemberDAO memberDAO;

    @Autowired
    public ConnectServiceImpl(ConnectDAO connectDAO, MemberDAO memberDAO) {
        this.connectDAO = connectDAO;
        this.memberDAO = memberDAO;
    }

    @Override
    public ConnectDTO getConnect(long connectSeq) {
        Connect connect = connectDAO.SelectConnectById(connectSeq);
        ConnectDTO connectDTO = new ConnectDTO();

        connectDTO.setConnectSeq(connect.getConnectSeq());
        connectDTO.setCreateTime(connect.getCreateTime());
        connectDTO.setFollowing(connect.getFollowing().getMemberSeq());
        connectDTO.setFollower(connect.getFollower().getMemberSeq());

        return connectDTO;
    }

    @Override
    public ConnectDTO insertConnect(ConnectDTO connectDTO) throws Exception {
        Connect connect = new Connect();

        connect.setConnectSeq(connectDTO.getConnectSeq());
        connect.setCreateTime(connectDTO.getCreateTime());

        // insert FK
        Member mem1 = memberDAO.SelectMemberById(connectDTO.getFollower());
        connect.setFollower(mem1);

        Member mem2 = memberDAO.SelectMemberById(connectDTO.getFollowing());
        connect.setFollowing(mem2);

        Connect savedConnect = connectDAO.InsertConnect(connect);
        ConnectDTO rConnectDTO = new ConnectDTO();

        rConnectDTO.setConnectSeq(savedConnect.getConnectSeq());
        rConnectDTO.setCreateTime(savedConnect.getCreateTime());
        rConnectDTO.setFollower(savedConnect.getFollower().getMemberSeq());
        rConnectDTO.setFollowing(savedConnect.getFollowing().getMemberSeq());

        return rConnectDTO;
    }

    @Override
    public void deleteConnect(long connectSeq) throws Exception {
        connectDAO.DeleteConnectById(connectSeq);
    }
}
