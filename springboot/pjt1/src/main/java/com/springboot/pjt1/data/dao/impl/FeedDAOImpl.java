package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        return feedRepository.save(feed);
    }

    @Override
    public Feed SelectFeedById(long feedSeq) {
        return feedRepository.getById(feedSeq);
    }

    @Override
    public List<Feed> SelectFeedAll() {
        return feedRepository.findAll();
    }

    @Override
    public List<Feed> SelectFeedAllOrderByHeart() {
        return feedRepository.findAllByOrderByHeartAsc();
    }

    @Override
    public List<Feed> SelectFeedAllOrderByCreateTime() {
        return feedRepository.findAllByOrderByCreateTimeDesc();
    }

    @Override
    public List<Feed> SelectFeedAllOrderByHeartByCity(long MachineLocationSeq) {
        return feedRepository.findByMachineLocationSeqOrderByHeart(MachineLocationSeq);
    }

    @Override
    public List<Feed> SelectFeedAllOrderByCreateTimeByCity(long MachineLocationSeq) {
        return feedRepository.findByMachineLocationSeqOrderByCreateTimeDesc(MachineLocationSeq);
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

    @Override
    public List<Feed> SelectFollowerFeed(long memberSeq) {
        List<Feed> feeds = feedRepository.findByMemberSeq(memberSeq);

        return feeds;
    }

    @Override
    public List<Feed> SelectFeedByMemberSeqs(List<Long> memberSeqs) {
        return feedRepository.findByMemberSeqs(memberSeqs);
    }

    @Override
    public void DeleteFeedByMemberSeq(long memberSeq) {
        List<Feed> feeds = feedRepository.findAllByMemberSeq(memberSeq);

        for(Feed feed:feeds)
            feedRepository.delete(feed);
    }

    @Override
    public List<Feed> SelectFeedAllByMemberSeq(long memberSeq) {
        List<Feed> feeds = feedRepository.findAllByOrderByCreateTimeDesc();
        List<Feed> rfeed = new ArrayList<>();

        for (Feed feed:feeds)
            if (feed.getMemberSeq() == memberSeq)
                rfeed.add(feed);

        return rfeed;
    }

    @Override
    public List<Feed> SelectFeedByMemberSeq(Long mSeq) {
        return feedRepository.findByMemberSeq(mSeq);
    }

    @Override
    public boolean isExistByFeedSeq(long feedSeq) {
        return feedRepository.existsByFeedSeq(feedSeq);
    }


}
