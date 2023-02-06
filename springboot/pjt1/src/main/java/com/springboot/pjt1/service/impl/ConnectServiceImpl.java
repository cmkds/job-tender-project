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

import java.util.List;

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

        return connectDTO;
    }

    @Override
    public boolean getIsFollow(long srcMemberSeq, long dstMemberSeq) {
        boolean isFollow = connectDAO.SelectConnectByMemberIds(srcMemberSeq, dstMemberSeq);

        return isFollow;
    }

    @Override
    public long getConnectSeq(long srcMemberSeq, long dstMemberSeq) {
        long connectSeq = connectDAO.SelectConnectSeqByMemberId(srcMemberSeq, dstMemberSeq);

        return connectSeq;
    }

    @Override
    public ConnectDTO insertConnect(ConnectDTO connectDTO) throws Exception {
        Connect connect = new Connect();

        connect.setConnectSeq(connectDTO.getConnectSeq());
        connect.setCreateTime(connectDTO.getCreateTime());

        Connect savedConnect = connectDAO.InsertConnect(connect);
        ConnectDTO rConnectDTO = new ConnectDTO();

        rConnectDTO.setConnectSeq(savedConnect.getConnectSeq());
        rConnectDTO.setCreateTime(savedConnect.getCreateTime());

        return rConnectDTO;
    }

    @Override
    public void deleteConnect(long connectSeq) throws Exception {
        connectDAO.DeleteConnectById(connectSeq);
    }

    @Override
    public List<Long> getFollowings(long memberSeq) {
        return connectDAO.SelectFollowingByMemberSeq(memberSeq);
    }

    @Override
    public List<Long> getFollowers(long memberSeq) {
        return connectDAO.SelectFollowerByMemberSeq(memberSeq);
    }

    @Override
    public void deleteConnectByMemberSeq(long memberSeq) {
        connectDAO.DeleteFollowByMemberSeq(memberSeq);
    }
}
