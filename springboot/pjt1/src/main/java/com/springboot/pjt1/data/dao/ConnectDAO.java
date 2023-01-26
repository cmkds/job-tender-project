package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Connect;

public interface ConnectDAO {
    Connect InsertConnect(Connect connect) throws Exception ;
    Connect SelectConnectById(long connectSeq);
    Connect UpdateConnectById(long connectSeq, long follower, long following) throws Exception;
    void DeleteConnectById(long connectSeq) throws Exception ;
}