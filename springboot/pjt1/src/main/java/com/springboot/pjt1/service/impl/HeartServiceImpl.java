package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.HeartDAO;
import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.HeartDTO;
import com.springboot.pjt1.data.dto.custom.HeartInputDTO;
import com.springboot.pjt1.data.entity.Heart;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HeartServiceImpl implements HeartService {
    private final HeartDAO heartDAO;
    private final FeedDAO feedDAO;
    private final MemberDAO memberDAO;

    @Autowired
    public HeartServiceImpl(HeartDAO heartDAO, FeedDAO feedDAO, MemberDAO memberDAO) {
        this.heartDAO = heartDAO;
        this.feedDAO = feedDAO;
        this.memberDAO = memberDAO;
    }
    @Override
    public HeartDTO getHeart(long heartSeq) {
        Heart heart = heartDAO.SelectHeartById(heartSeq);
        HeartDTO heartDTO = new HeartDTO();

        heartDTO.setHeartSeq(heart.getHeartSeq());
        heartDTO.setCreateTime(heart.getCreateTime());

        return heartDTO;
    }

    @Override
    public HeartDTO getHeartByFeedAndMember(long feedSeq, long memberSeq) {
        Heart heart = heartDAO.SelectHeartByFeedAndMember(feedSeq, memberSeq);

        if (heart == null)
            return null;

        HeartDTO heartDTO = new HeartDTO();

        heartDTO.setHeartSeq(heart.getHeartSeq());
        heartDTO.setCreateTime(heart.getCreateTime());

        return heartDTO;
    }

    public HeartDTO getHeartByFeedAndMember(long heartSeq) {
        Heart heart = heartDAO.SelectHeartById(heartSeq);
        HeartDTO heartDTO = new HeartDTO();

        heartDTO.setHeartSeq(heart.getHeartSeq());
        heartDTO.setCreateTime(heart.getCreateTime());

        return heartDTO;
    }
    @Override
    public HeartDTO insertHeart(HeartInputDTO heartInputDTO) throws Exception {
        Heart heart = new Heart();

        heart.setHeartSeq(heartInputDTO.getHeartSeq());
        heart.setFeedSeq(heartInputDTO.getFeedSeq());
        heart.setMemberSeq(heartInputDTO.getMemberSeq());
        heart.setCreateTime(new Date());

        Heart savedHeart = heartDAO.insertHeart(heart);
        HeartDTO rHeartDTO = new HeartDTO();

        rHeartDTO.setHeartSeq(savedHeart.getHeartSeq());
        rHeartDTO.setCreateTime(savedHeart.getCreateTime());
        rHeartDTO.setMemberSeq(savedHeart.getMemberSeq());
        rHeartDTO.setFeedSeq(savedHeart.getFeedSeq());

        return rHeartDTO;
    }
    
    @Override
    public void deleteHeart(long heartSeq) throws Exception {
        heartDAO.deleteHeartById(heartSeq);
    }

    @Override
    public void deleteHeartByMemberSeq(long memberSeq) {
        heartDAO.deleteHeartByMemberSeq(memberSeq);
    }

    @Override
    public HeartDTO getHeartByFeed(long feedSeq) {
        return heartDAO.SelectHeartByFeed(feedSeq);
    }

    @Override
    public List<HeartDTO> getHeartsByFeed(long feedSeq) {
        List<HeartDTO> heartDTOs = new ArrayList<>();
        List<Heart> hearts = heartDAO.SelectHeartsByFeedSeq(feedSeq);

        for(Heart heart : hearts){
            HeartDTO heartDTO = new HeartDTO();
            heartDTO.setFeedSeq(heart.getFeedSeq());
            heartDTO.setMemberSeq(heart.getMemberSeq());
            heartDTO.setHeartSeq(heart.getHeartSeq());
            heartDTO.setCreateTime(heart.getCreateTime());

            heartDTOs.add(heartDTO);
        }

        return heartDTOs;
    }

    @Override
    public List<HeartDTO> getHeartAll() {
        List<HeartDTO> heartDTOs = new ArrayList<>();
        List<Heart> hearts = heartDAO.SelectHeartAll();

        for(Heart heart : hearts){
            HeartDTO heartDTO = new HeartDTO();
            heartDTO.setFeedSeq(heart.getFeedSeq());
            heartDTO.setMemberSeq(heart.getMemberSeq());
            heartDTO.setHeartSeq(heart.getHeartSeq());
            heartDTO.setCreateTime(heart.getCreateTime());

            heartDTOs.add(heartDTO);
        }

        return heartDTOs;
    }
}
