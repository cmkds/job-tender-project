package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.ConnectDAO;
import com.springboot.pjt1.data.entity.Connect;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.repository.ConnectRepository;
import com.springboot.pjt1.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.ConnectException;
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
        List<Connect> members = connectRepository.findAllByFollower(memberSeq);
        List<Long> memberSeqs = new ArrayList<>();

        for (Connect member:members)
            memberSeqs.add(member.getFollowing());

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

    @Override
    public void DeleteFollowByMemberSeq(long memberSeq) {
        List<Connect> connects = connectRepository.findAllByFollower(memberSeq);
        for(Connect connect : connects)
            connectRepository.delete(connect);

        connects = connectRepository.findAllByFollowing(memberSeq);
        for (Connect connect: connects)
            connectRepository.delete(connect);

    }

    @Override
    public List<Long> SelectFollowerByMemberSeq(long memberSeq) {
        List<Connect> members = connectRepository.findAllByFollowing(memberSeq);
        List<Long> memberSeqs = new ArrayList<>();

        for (Connect member:members)
            memberSeqs.add(member.getFollower());

        return memberSeqs;
    }
}
