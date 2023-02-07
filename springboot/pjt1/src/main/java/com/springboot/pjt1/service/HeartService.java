package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.HeartDTO;
import com.springboot.pjt1.data.dto.custom.HeartInputDTO;

import java.util.List;

public interface HeartService {
    HeartDTO getHeart(long heartSeq);
    HeartDTO getHeartByFeedAndMember(long feedSeq, long memberSeq);
    HeartDTO insertHeart(HeartInputDTO heartInputDTO) throws Exception;
    void deleteHeart(long heartSeq)throws Exception;
    void deleteHeartByMemberSeq(long memberSeq);
    HeartDTO getHeartByFeed(long feedSeq);
    List<HeartDTO> getHeartsByFeed(long feedSeq);
}
