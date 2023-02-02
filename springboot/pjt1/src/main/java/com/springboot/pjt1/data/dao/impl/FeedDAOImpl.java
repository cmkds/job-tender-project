package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class FeedDAOImpl implements FeedDAO {
    private FeedRepository feedRepository;

    @Autowired
    public FeedDAOImpl(FeedRepository feedRepository){
        this.feedRepository = feedRepository;
    }
    
    @Override
    public Feed InsertFeed(Feed feed) throws Exception {
        Feed savedFeed = feedRepository.save(feed);

        return savedFeed;
    }

    @Override
    public Feed SelectFeedById(long feedSeq) {
        Feed selectedFeed = feedRepository.getById(feedSeq);

        return selectedFeed;
    }

    @Override
    public Feed UpdateFeedById(long feedSeq, long memberSeq, String content, String post) throws Exception {
        // get data using ID
        Optional<Feed> selectedFeed = feedRepository.findById(feedSeq);
        Feed updatedFeed;

        if(selectedFeed.isPresent()){
            Feed feed = selectedFeed.get();

            feed.setContent(content);
            feed.setPost(post);

            feed.setCreateTime(new Date());
            feed.setModifyTime(new Date());

            updatedFeed = feedRepository.save(feed);
        }

        else
            throw new Exception();

        return updatedFeed;
    }

    @Override
    public void DeleteFeedById(long feedSeq) throws Exception {
        Optional<Feed> selectedFeed = feedRepository.findById(feedSeq);

        if (selectedFeed.isPresent()){
            Feed feed = selectedFeed.get();
            feedRepository.delete(feed);
        }

        else
            throw new Exception();
    }
}
