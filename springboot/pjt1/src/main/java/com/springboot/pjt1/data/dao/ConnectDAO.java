package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Connect;

public interface ConnectDAO {
    Connect InsertConnect(Connect connect) throws Exception ;
    Connect SelectConnectById(long connectSeq);
    void DeleteConnectById(long connectSeq) throws Exception ;
}
