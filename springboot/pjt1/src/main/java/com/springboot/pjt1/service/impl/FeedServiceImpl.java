package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.dao.MachineLocationDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.ConnectDTO;
import com.springboot.pjt1.data.dto.FeedDTO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.entity.Connect;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.MachineLocation;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.FeedService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {
    private final FeedDAO feedDAO;
    private final MemberDAO memberDAO;
    private final MachineLocationDAO machineLocationDAO;

    public FeedServiceImpl(FeedDAO feedDAO, MemberDAO memberDAO, MachineLocationDAO machineLocationDAO) {
        this.feedDAO = feedDAO;
        this.memberDAO = memberDAO;
        this.machineLocationDAO = machineLocationDAO;
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
    public List<FeedDTO> getFeedAll() {
        List<Feed> feeds = feedDAO.SelectFeedAll();
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feeds.get(i).getFeedSeq());
            feedDTO.setContent(feeds.get(i).getContent());
            feedDTO.setPost(feeds.get(i).getPost());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setHeart(feeds.get(i).getHeart());

            feedDTOs.add(feedDTO);
        }

        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByHeart() {
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByHeart();
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feeds.get(i).getFeedSeq());
            feedDTO.setContent(feeds.get(i).getContent());
            feedDTO.setPost(feeds.get(i).getPost());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setHeart(feeds.get(i).getHeart());

            feedDTOs.add(feedDTO);
        }

        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByCreateTime() {
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByCreateTime();
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feeds.get(i).getFeedSeq());
            feedDTO.setContent(feeds.get(i).getContent());
            feedDTO.setPost(feeds.get(i).getPost());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setHeart(feeds.get(i).getHeart());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByHeartByCity(String city) {
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByHeartByCity(city);
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feeds.get(i).getFeedSeq());
            feedDTO.setContent(feeds.get(i).getContent());
            feedDTO.setPost(feeds.get(i).getPost());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setHeart(feeds.get(i).getHeart());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByCreateTimeByCity(String city) {
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByCreateTimeByCity(city);
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feeds.get(i).getFeedSeq());
            feedDTO.setContent(feeds.get(i).getContent());
            feedDTO.setPost(feeds.get(i).getPost());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setHeart(feeds.get(i).getHeart());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFollowingFeed(List<Long> memberSeqs) {
        List<Feed> feeds = feedDAO.SelectFeedByMemberSeqs(memberSeqs);
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feeds.get(i).getFeedSeq());
            feedDTO.setContent(feeds.get(i).getContent());
            feedDTO.setPost(feeds.get(i).getPost());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setHeart(feeds.get(i).getHeart());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
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

        Feed savedFeed = feedDAO.InsertFeed(feed);
        FeedDTO rFeedDTO = new FeedDTO();

        rFeedDTO.setFeedSeq(savedFeed.getFeedSeq());
        rFeedDTO.setContent(savedFeed.getContent());
        rFeedDTO.setPost(savedFeed.getPost());
        rFeedDTO.setCreateTime(savedFeed.getCreateTime());
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
        rFeedDTO.setCreateTime(updatedFeed.getCreateTime());
        rFeedDTO.setHeart(updatedFeed.getHeart());

        return rFeedDTO;
    }

    @Override
    public void deleteFeed(long feedSeq) throws Exception {
        feedDAO.DeleteFeedById(feedSeq);
    }

    @Override
    public List<FeedDTO> getFollowerFeed(long memberSeq) {
        List<Feed> feeds = feedDAO.SelectFollowerFeed(memberSeq);
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feeds.get(i).getFeedSeq());
            feedDTO.setContent(feeds.get(i).getContent());
            feedDTO.setPost(feeds.get(i).getPost());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setCreateTime(feeds.get(i).getCreateTime());
            feedDTO.setHeart(feeds.get(i).getHeart());

            feedDTOs.add(feedDTO);
        }

        return feedDTOs;
    }

    @Override
    public FeedDTO StoreToFeed(StoreDTO storeDTO, String content) {
        FeedDTO feedDTO = new FeedDTO();

        feedDTO.setMachineLocationSeq(storeDTO.getMachineLocationSeq());
        feedDTO.setMemberSeq(storeDTO.getMemberSeq());
        feedDTO.setModifyTime(new Date());
        feedDTO.setPost(storeDTO.getPost());
        feedDTO.setHeart(0);
        feedDTO.setCreateTime(new Date());
        feedDTO.setContent(content);

        return feedDTO;
    }


}
