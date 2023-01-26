package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Feed;

public interface FeedDAO {
    Feed InsertFeed(Feed feed) throws Exception;
    Feed SelectFeedById(long feedSeq);
    Feed UpdateFeedById(long feedSeq, long memberSeq, String content, String post) throws Exception;
    void DeleteFeedById(long feedSeq) throws Exception;
}
