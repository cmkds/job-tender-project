package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Connect;

public interface ConnectDAO {
    Connect InsertConnect(Connect connect) throws Exception ;
    Connect SelectConnectById(long connectSeq);

    boolean SelectConnectByMemberIds(long srcMemberSeq, long dstMemberSeq);
    long SelectConnectSeqByMemberIds(long srcMemberSeq, long dstMemberSeq);
    void DeleteConnectById(long connectSeq) throws Exception ;
}
