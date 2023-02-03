package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Connect;

import java.util.List;

public interface ConnectDAO {
    Connect InsertConnect(Connect connect) throws Exception ;
    Connect SelectConnectById(long connectSeq);
    boolean SelectConnectByMemberIds(long srcMemberSeq, long dstMemberSeq);
    List<Long> SelectFollowingByMemberSeq(long memberSeq);
    long SelectConnectSeqByMemberId(long srcMemberSeq, long dstMemberSeq);
    void DeleteConnectById(long connectSeq) throws Exception ;
}
