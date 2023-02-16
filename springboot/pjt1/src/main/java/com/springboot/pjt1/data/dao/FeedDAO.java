package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Feed;

import java.util.List;

public interface FeedDAO {
    Feed InsertFeed(Feed feed) throws Exception;
    Feed SelectFeedById(long feedSeq);
    List<Feed> SelectFeedAll();
    List<Feed> SelectFeedAllOrderByHeart();
    List<Feed> SelectFeedAllOrderByCreateTime();
    List<Feed> SelectFeedAllOrderByHeartByCity(long MachineLocationSeq);
    List<Feed> SelectFeedAllOrderByCreateTimeByCity(long MachineLocationSeq);
    Feed UpdateFeedById(long feedSeq, long memberSeq, String content, String post) throws Exception;
    void DeleteFeedById(long feedSeq) throws Exception;
    List<Feed> SelectFollowerFeed(long memberSeq);
    List<Feed> SelectFeedByMemberSeqs(List<Long> memberSeqs);
    void DeleteFeedByMemberSeq(long memberSeq);
    List<Feed> SelectFeedAllByMemberSeq(long memberSeq);

    List<Feed> SelectFeedByMemberSeq(Long mSeq);
}
