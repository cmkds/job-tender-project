package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.FeedDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FeedService {
    FeedDTO getFeed(long feedSeq);
    List<FeedDTO> getFeedAll();
    List<FeedDTO> getFeedAllOrderByHeart();
    List<FeedDTO> getFeedAllOrderByCreateTime();
    List<FeedDTO> getFeedAllOrderByHeartByCity(String city);
    List<FeedDTO> getFeedAllOrderByCreateTimeByCity(String city);
    FeedDTO insertFeed(FeedDTO feedDTO)throws Exception;
    FeedDTO updateFeed(long feedSeq, long memberSeq, String content, String post)throws Exception;
    void deleteFeed(long feedSeq)throws Exception;
    List<FeedDTO> getFollowerFeed(long memberSeq);
}
