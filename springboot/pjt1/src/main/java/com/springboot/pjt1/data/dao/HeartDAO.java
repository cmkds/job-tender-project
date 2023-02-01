package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Heart;

public interface HeartDAO {
    Heart insertHeart(Heart heart) throws Exception;
    Heart SelectHeartById(Long heartSeq);
    void deleteHeartById(Long heartSeq) throws Exception;
}
