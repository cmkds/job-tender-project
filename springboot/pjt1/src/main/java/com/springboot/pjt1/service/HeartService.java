package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.HeartDTO;

public interface HeartService {
    HeartDTO getHeart(long heartSeq);
    HeartDTO getHeartByFeedAndMember(long feedSeq, long memberSeq);
    HeartDTO insertHeart(HeartDTO heartDTO) throws Exception;
    void deleteHeart(long heartSeq)throws Exception;
}
