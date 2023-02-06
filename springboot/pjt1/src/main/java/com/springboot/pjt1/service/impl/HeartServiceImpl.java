package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.HeartDAO;
import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.HeartDTO;
import com.springboot.pjt1.data.entity.Heart;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public HeartDTO insertHeart(HeartDTO heartDTO) throws Exception {
        Heart heart = new Heart();

        heart.setHeartSeq(heartDTO.getHeartSeq());
        heart.setCreateTime(heartDTO.getCreateTime());

        Heart savedHeart = heartDAO.insertHeart(heart);
        HeartDTO rHeartDTO = new HeartDTO();

        rHeartDTO.setHeartSeq(savedHeart.getHeartSeq());
        rHeartDTO.setCreateTime(savedHeart.getCreateTime());

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
}
