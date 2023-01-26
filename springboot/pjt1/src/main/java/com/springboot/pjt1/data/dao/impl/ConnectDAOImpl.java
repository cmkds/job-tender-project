package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.ConnectDAO;
import com.springboot.pjt1.data.entity.Connect;
import com.springboot.pjt1.data.repository.ConnectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ConnectDAOImpl implements ConnectDAO {
    private ConnectRepository connectRepository;

    @Autowired
    public ConnectDAOImpl(ConnectRepository connectRepository){
        this.connectRepository = connectRepository;
    }

    @Override
    public Connect InsertConnect(Connect connect) throws Exception {
        Connect savedConnect = connectRepository.save(connect);

        return savedConnect;
    }

    @Override
    public Connect SelectConnectById(long connectSeq) {
        Connect selectedConnect = connectRepository.getById(connectSeq);

        return selectedConnect;
    }

    @Override
    public Connect UpdateConnectById(long connectSeq, long follower, long following)throws Exception{
        // get data using ID
        Optional<Connect> selectedConnect = connectRepository.findById(connectSeq);
        Connect updatedConnect;

        if(selectedConnect.isPresent()){
            Connect connect = selectedConnect.get();

            connect.setFollower(follower);
            connect.setFollowing(following);
            connect.setCreateTime(new Date());

            updatedConnect = connectRepository.save(connect);
        }

        else
            throw new Exception();

        return updatedConnect;
    }

    @Override
    public void DeleteConnectById(long connectSeq) throws Exception {
        Optional<Connect> selectedConnect = connectRepository.findById(connectSeq);

        if (selectedConnect.isPresent()){
            Connect connect = selectedConnect.get();
            connectRepository.delete(connect);
        }

        else
            throw new Exception();
    }
}