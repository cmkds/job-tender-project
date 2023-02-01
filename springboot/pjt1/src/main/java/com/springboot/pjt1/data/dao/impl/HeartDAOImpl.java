package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.HeartDAO;
import com.springboot.pjt1.data.entity.Heart;
import com.springboot.pjt1.data.repository.HeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    public Heart SelectHeartById(Long HeartSeq) {
        Heart SelectedHeart = heartRepository.getById(HeartSeq);

        return SelectedHeart;
    }

    @Override
    public void deleteHeartById(Long HeartSeq) throws Exception {
        Optional<Heart> SelectedHeart = heartRepository.findById(HeartSeq);
        
        if (SelectedHeart.isPresent()){
            Heart heart = SelectedHeart.get();
            heartRepository.delete(heart);
        }
        
        else
            throw new Exception();
    }
    // jpa 영접

}
