package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MachineDataDAO;
import com.springboot.pjt1.data.dto.MachineDataDTO;
import com.springboot.pjt1.data.entity.MachineData;
import com.springboot.pjt1.service.MachineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineDataServiceImpl implements MachineDataService {
    private final MachineDataDAO machineDataDAO;

    @Autowired
    public MachineDataServiceImpl(MachineDataDAO machineDataDAO) {
        this.machineDataDAO = machineDataDAO;
    }
    @Override
    public MachineDataDTO getMachineData(long machineDataSeq) {
        MachineData machineData = machineDataDAO.SelectMachineDataById(machineDataSeq);
        MachineDataDTO machineDataDTO = new MachineDataDTO();

        machineDataDTO.setMachineDataSeq(machineData.getMachineDataSeq());
        machineDataDTO.setCreateTime(machineData.getCreateTime());
        //machineDataDTO.setMachineSeq(machineData.getMachineSeq());
        machineDataDTO.setVideo(machineData.getVideo());
        machineDataDTO.setPost(machineData.getPost());
        machineDataDTO.setVoice(machineData.getVoice());
        machineDataDTO.setPhoto(machineData.getPhoto());

        return machineDataDTO;
    }

    @Override
    public MachineDataDTO insertMachineData(MachineDataDTO machineDataDTO) throws Exception {
        MachineData machineData = new MachineData();

        machineData.setMachineDataSeq(machineDataDTO.getMachineDataSeq());
        machineData.setMachineDataSeq(machineDataDTO.getMachineDataSeq());
        machineData.setCreateTime(machineDataDTO.getCreateTime());
        //machineData.setMachineSeq(machineDataDTO.getMachineSeq());
        machineData.setVideo(machineDataDTO.getVideo());
        machineData.setPost(machineDataDTO.getPost());
        machineData.setVoice(machineDataDTO.getVoice());
        machineData.setPhoto(machineDataDTO.getPhoto());

        MachineData savedMachineData = machineDataDAO.InsertMachineData(machineData);
        MachineDataDTO rMachineDataDTO = new MachineDataDTO();

        rMachineDataDTO.setMachineDataSeq(savedMachineData.getMachineDataSeq());
        rMachineDataDTO.setMachineDataSeq(savedMachineData.getMachineDataSeq());
        rMachineDataDTO.setCreateTime(savedMachineData.getCreateTime());
        //rMachineDataDTO.setMachineSeq(savedMachineData.getMachineSeq());
        rMachineDataDTO.setVideo(savedMachineData.getVideo());
        rMachineDataDTO.setPost(savedMachineData.getPost());
        rMachineDataDTO.setVoice(savedMachineData.getVoice());
        rMachineDataDTO.setPhoto(savedMachineData.getPhoto());

        return rMachineDataDTO;
    }

    @Override
    public MachineDataDTO updateMachineData(long machineDataSeq) throws Exception {
        return null;
    }

    @Override
    public void deleteMachineData(long machineDataSeq) throws Exception {
        machineDataDAO.DeleteMachineDataById(machineDataSeq);
    }
}
