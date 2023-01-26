package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.FeedDTO;
import org.springframework.stereotype.Service;

public interface FeedService {
    FeedDTO getFeed(long feedSeq);
    FeedDTO insertFeed(FeedDTO feedDTO)throws Exception;
    FeedDTO updateFeed(long feedSeq, long memberSeq, String content, String post)throws Exception;
    void deleteFeed(long feedSeq)throws Exception;
}
