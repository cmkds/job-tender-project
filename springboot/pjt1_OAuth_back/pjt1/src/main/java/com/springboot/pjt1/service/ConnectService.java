package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.ConnectDTO;
import com.springboot.pjt1.data.dto.custom.ConnectInputDTO;
import com.springboot.pjt1.data.entity.Connect;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ConnectService {
    ConnectDTO getConnect(long connectSeq);
    boolean getIsFollow(long srcMemberSeq, long dstMemberSeq);
    long getConnectSeq(long srcMemberSeq, long dstMemberSeq);
    ConnectDTO insertConnect(ConnectInputDTO connectInputDTO)throws Exception;
    void deleteConnect(long connectSeq)throws Exception;
    List<Long> getFollowings(long memberSeq);
    List<Long> getFollowers(long memberSeq);
    void deleteConnectByMemberSeq(long memberSeq);
}
