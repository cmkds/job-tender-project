package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.ConnectDTO;
import com.springboot.pjt1.data.dto.FeedDTO;
import com.springboot.pjt1.data.entity.Connect;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.FeedService;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {
    private final FeedDAO feedDAO;
    private final MemberDAO memberDAO;

    public FeedServiceImpl(FeedDAO feedDAO, MemberDAO memberDAO) {
        this.feedDAO = feedDAO;
        this.memberDAO = memberDAO;
    }
    @Override
    public FeedDTO getFeed(long feedSeq) {
        Feed feed = feedDAO.SelectFeedById(feedSeq);
        FeedDTO feedDTO = new FeedDTO();

        feedDTO.setFeedSeq(feed.getFeedSeq());
        feedDTO.setContent(feed.getContent());
        feedDTO.setPost(feed.getPost());
        feedDTO.setCreateTime(feed.getCreateTime());
        feedDTO.setCreateTime(feed.getCreateTime());
        feedDTO.setHeart(feed.getHeart());

        return feedDTO;
    }

    @Override
    public FeedDTO insertFeed(FeedDTO feedDTO) throws Exception {
        Feed feed = new Feed();

        feed.setFeedSeq(feedDTO.getFeedSeq());
        feed.setContent(feedDTO.getContent());
        feed.setPost(feedDTO.getPost());
        feed.setCreateTime(feedDTO.getCreateTime());
        feed.setCreateTime(feedDTO.getCreateTime());
        feed.setHeart(feedDTO.getHeart());

        Member mem = memberDAO.SelectMemberById(feedDTO.getMemberSeq());
        feed.setMember(mem);

        Feed savedFeed = feedDAO.InsertFeed(feed);
        FeedDTO rFeedDTO = new FeedDTO();

        rFeedDTO.setFeedSeq(savedFeed.getFeedSeq());
        rFeedDTO.setContent(savedFeed.getContent());
        rFeedDTO.setPost(savedFeed.getPost());
        rFeedDTO.setCreateTime(savedFeed.getCreateTime());
        //rFeedDTO.setMemberSeq(savedFeed.getMemberSeq());
        rFeedDTO.setCreateTime(savedFeed.getCreateTime());
        rFeedDTO.setHeart(savedFeed.getHeart());

        return rFeedDTO;
    }

    @Override
    public FeedDTO updateFeed(long feedSeq, long memberSeq, String content, String post) throws Exception {
        Feed updatedFeed = feedDAO.UpdateFeedById(feedSeq,memberSeq,content, post);
        FeedDTO rFeedDTO = new FeedDTO();

        rFeedDTO.setFeedSeq(updatedFeed.getFeedSeq());
        rFeedDTO.setContent(updatedFeed.getContent());
        rFeedDTO.setPost(updatedFeed.getPost());
        rFeedDTO.setCreateTime(updatedFeed.getCreateTime());
        //rFeedDTO.setMemberSeq(updatedFeed.getMemberSeq());
        rFeedDTO.setCreateTime(updatedFeed.getCreateTime());
        rFeedDTO.setHeart(updatedFeed.getHeart());

        return rFeedDTO;
    }

    @Override
    public void deleteFeed(long feedSeq) throws Exception {
        feedDAO.DeleteFeedById(feedSeq);
    }
}
