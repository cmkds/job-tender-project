package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.ConnectDAO;
import com.springboot.pjt1.data.entity.Connect;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.repository.ConnectRepository;
import com.springboot.pjt1.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ConnectDAOImpl implements ConnectDAO {
    private ConnectRepository connectRepository;

    @Autowired
    public ConnectDAOImpl(ConnectRepository connectRepository){
        this.connectRepository = connectRepository;
    }

    @Override
    public Connect InsertConnect(Connect connect) throws Exception {
        return connectRepository.save(connect);
    }

    @Override
    public Connect SelectConnectById(long connectSeq) {
        return connectRepository.getById(connectSeq);
    }

    @Override
    public boolean SelectConnectByMemberIds(long srcMemberSeq, long dstMemberSeq) {
        return connectRepository.existsByFollowerAndFollowing(srcMemberSeq, dstMemberSeq);
    }

    @Override
    public List<Long> SelectFollowingByMemberSeq(long memberSeq) {
        List<Connect> members = connectRepository.findByFollower(memberSeq);
        List<Long> memberSeqs = new ArrayList<>();

        for (int i = 0; i < members.size(); i++){
            memberSeqs.add(members.get(i).getFollower());
        }

        return memberSeqs;
    }

    @Override
    public long SelectConnectSeqByMemberId(long srcMemberSeq, long dstMemberSeq) {
        return connectRepository.findByFollowingAndFollower(srcMemberSeq, dstMemberSeq).getConnectSeq();
    }

    @Override
    public void DeleteConnectById(long connectSeq) throws Exception {
        Optional<Connect> selectedConnect = connectRepository.findById(connectSeq);

        if (selectedConnect.isPresent()){
            Connect connect = selectedConnect.get();
            connectRepository.delete(connect);
        }

        else
            throw new Exception();
    }
}
