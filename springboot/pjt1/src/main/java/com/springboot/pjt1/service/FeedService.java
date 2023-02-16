package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.FeedDTO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.dto.custom.FeedInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FeedService {
    FeedDTO getFeed(long feedSeq);
    List<FeedDTO> getFeedAll();
    List<FeedDTO> getFeedAllOrderByHeart();
    List<FeedDTO> getFeedAllOrderByCreateTime();

    List<FeedDTO> getFollowingFeed(List<Long> memberSeqs);
    FeedDTO insertFeed(FeedInputDTO feedInputDTO)throws Exception;
    FeedDTO updateFeed(long feedSeq, long memberSeq, String content, String post)throws Exception;
    void deleteFeed(long feedSeq)throws Exception;
    List<FeedDTO> getFollowerFeed(long memberSeq);

    FeedDTO StoreToFeed(StoreDTO storeDTO, String content);

    void deleteFeedByMemberSeq(long memberSeq);

    List<FeedDTO> getFeedAllOrderByHeartByMachineLocationSeq(long machineLocationSeq);

    List<FeedDTO> getFeedAllOrderByCreateTimeByMachineLocationSeq(long machineLocationSeq);

    FeedInputDTO ConvertToInputDTO(FeedDTO feedDTO);

    List<FeedDTO> getFeedAllByMemberSeq(long memberSeq);

<<<<<<< HEAD
    boolean isExistByFeedSeq(long feedSeq);
=======
    List<FeedDTO> getFollowingFeedAll(List<Long> memberSeqs);
>>>>>>> c853637d027a68900655a2ffd6803abfa6ac5860
}
