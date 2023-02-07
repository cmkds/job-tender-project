package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.HeartDAO;
import com.springboot.pjt1.data.dto.HeartDTO;
import com.springboot.pjt1.data.entity.Heart;
import com.springboot.pjt1.data.repository.HeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class HeartDAOImpl implements HeartDAO{
    private HeartRepository heartRepository;

    @Autowired
    public HeartDAOImpl(HeartRepository heartRepository){
        this.heartRepository = heartRepository;
    }

    @Override
    public Heart insertHeart(Heart heart) throws Exception {

        Heart savedHeart = heartRepository.save(heart);

        return savedHeart;
    }

    @Override
    public Heart SelectHeartById(long HeartSeq) {
        Heart SelectedHeart = heartRepository.getById(HeartSeq);

        return SelectedHeart;
    }

    @Override
    public Heart SelectHeartByFeedAndMember(long feedSeq, long memberSeq) {
        Optional<Heart> selectedHeart = heartRepository.findByFeedSeqAndMemberSeq(feedSeq, memberSeq);

        return selectedHeart.isPresent() ? selectedHeart.get() : null;
    }

    @Override
    public void deleteHeartById(long HeartSeq) throws Exception {
        Optional<Heart> SelectedHeart = heartRepository.findById(HeartSeq);
        
        if (SelectedHeart.isPresent()){
            Heart heart = SelectedHeart.get();
            heartRepository.delete(heart);
        }
        
        else
            throw new Exception();
    }

    @Override
    public void deleteHeartByMemberSeq(long memberSeq) {
        List<Heart> hearts = heartRepository.findAllByMemberSeq(memberSeq);

        for(Heart heart: hearts)
            heartRepository.delete(heart);
    }

    @Override
    public HeartDTO SelectHeartByFeed(long feedSeq) {
        return null;
    }

    @Override
    public List<Heart> SelectHeartsByFeedSeq(long feedSeq) {
        return heartRepository.findAllByFeedSeq(feedSeq);
    }
    // jpa 영접

}
