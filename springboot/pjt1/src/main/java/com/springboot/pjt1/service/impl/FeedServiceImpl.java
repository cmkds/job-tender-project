package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.dao.MachineLocationDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.ConnectDTO;
import com.springboot.pjt1.data.dto.FeedDTO;
import com.springboot.pjt1.data.dto.StoreDTO;
import com.springboot.pjt1.data.dto.custom.FeedInputDTO;
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
        feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());
        feedDTO.setMemberSeq(feed.getMemberSeq());

        return feedDTO;
    }

    @Override
    public List<FeedDTO> getFeedAll() {
        List<Feed> feeds = feedDAO.SelectFeedAll();
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (Feed feed : feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setContent(feed.getContent());
            feedDTO.setPost(feed.getPost());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());
            feedDTO.setMemberSeq(feed.getMemberSeq());

            feedDTOs.add(feedDTO);
        }

        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByHeart() {
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByHeart();
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for(Feed feed:feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setContent(feed.getContent());
            feedDTO.setPost(feed.getPost());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());

            feedDTOs.add(feedDTO);
        }

        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByCreateTime() {
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByCreateTime();
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for(Feed feed:feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setContent(feed.getContent());
            feedDTO.setPost(feed.getPost());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByHeartByMachineLocationSeq(long machineLocationSeq){
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByHeartByCity(machineLocationSeq);
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for(Feed feed:feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setContent(feed.getContent());
            feedDTO.setPost(feed.getPost());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
    }

    @Override
    public List<FeedDTO> getFeedAllOrderByCreateTimeByMachineLocationSeq(long machineLocationSeq) {
        List<Feed> feeds = feedDAO.SelectFeedAllOrderByCreateTimeByCity(machineLocationSeq);
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for(Feed feed:feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setContent(feed.getContent());
            feedDTO.setPost(feed.getPost());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
    }

    @Override
    public FeedInputDTO ConvertToInputDTO(FeedDTO feedDTO) {
        FeedInputDTO feedInputDTO = new FeedInputDTO();

        feedInputDTO.setContent(feedDTO.getContent());
        feedInputDTO.setFeedSeq(feedDTO.getFeedSeq());
        feedInputDTO.setMemberSeq(feedInputDTO.getMemberSeq());
        feedInputDTO.setPost(feedInputDTO.getPost());
        feedInputDTO.setMachineLocationSeq(feedInputDTO.getMachineLocationSeq());

        return feedInputDTO;
    }

    @Override
    public List<FeedDTO> getFeedAllByMemberSeq(long memberSeq) {
        List<FeedDTO> rFeedDTOs = new ArrayList<>();
        List<Feed> feeds = feedDAO.SelectFeedAllByMemberSeq(memberSeq);

        for(Feed feed:feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setModifyTime(feed.getModifyTime());
            feedDTO.setContent(feed.getContent());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setPost(feed.getPost());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());

            rFeedDTOs.add(feedDTO);
        }

        return rFeedDTOs;
    }

    @Override
    public boolean isExistByFeedSeq(long feedSeq) {
        return feedDAO.isExistByFeedSeq(feedSeq);
    }

    @Override
    public List<FeedDTO> getFollowingFeed(List<Long> memberSeqs) {
        List<Feed> feeds = feedDAO.SelectFeedByMemberSeqs(memberSeqs);
        List<FeedDTO> feedDTOs = new ArrayList<>();

        for (Feed feed:feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setContent(feed.getContent());
            feedDTO.setPost(feed.getPost());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setModifyTime(feed.getModifyTime());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());

            feedDTOs.add(feedDTO);
        }
        return feedDTOs;
    }



    @Override
    public FeedDTO insertFeed(FeedInputDTO feedInputDTO) throws Exception {
        Feed feed = new Feed();

        feed.setFeedSeq(feedInputDTO.getFeedSeq());
        feed.setContent(feedInputDTO.getContent());
        feed.setPost(feedInputDTO.getPost());
        feed.setCreateTime(new Date());
        feed.setModifyTime(new Date());
        feed.setHeart(0);
        feed.setMemberSeq(feedInputDTO.getMemberSeq());
        feed.setMachineLocationSeq(feedInputDTO.getMachineLocationSeq());

        Feed savedFeed = feedDAO.InsertFeed(feed);
        FeedDTO rFeedDTO = new FeedDTO();

        rFeedDTO.setFeedSeq(savedFeed.getFeedSeq());
        rFeedDTO.setContent(savedFeed.getContent());
        rFeedDTO.setPost(savedFeed.getPost());
        rFeedDTO.setCreateTime(savedFeed.getCreateTime());
        rFeedDTO.setCreateTime(savedFeed.getCreateTime());
        rFeedDTO.setHeart(savedFeed.getHeart());
        rFeedDTO.setMemberSeq(savedFeed.getMemberSeq());
        feed.setMachineLocationSeq(savedFeed.getMachineLocationSeq());

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
        rFeedDTO.setMemberSeq(updatedFeed.getMemberSeq());
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
        
        for (Feed feed:feeds){
            FeedDTO feedDTO = new FeedDTO();

            feedDTO.setFeedSeq(feed.getFeedSeq());
            feedDTO.setContent(feed.getContent());
            feedDTO.setPost(feed.getPost());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setCreateTime(feed.getCreateTime());
            feedDTO.setHeart(feed.getHeart());
            feedDTO.setMemberSeq(feed.getMemberSeq());
            feedDTO.setMachineLocationSeq(feed.getMachineLocationSeq());

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

    @Override
    public void deleteFeedByMemberSeq(long memberSeq) {
        feedDAO.DeleteFeedByMemberSeq(memberSeq);
    }




}
