package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.ConnectDTO;
import com.springboot.pjt1.data.entity.Connect;
import org.springframework.stereotype.Service;

public interface ConnectService {
    ConnectDTO getConnect(long connectSeq);
    ConnectDTO insertConnect(ConnectDTO connectDTO)throws Exception;
    void deleteConnect(long connectSeq)throws Exception;
}
