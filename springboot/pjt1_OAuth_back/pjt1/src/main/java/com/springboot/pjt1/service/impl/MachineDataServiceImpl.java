package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MachineDAO;
import com.springboot.pjt1.data.dao.MachineDataDAO;
import com.springboot.pjt1.data.dto.MachineDTO;
import com.springboot.pjt1.data.dto.MachineDataDTO;
import com.springboot.pjt1.data.dto.custom.MachineDataInputDTO;
import com.springboot.pjt1.data.entity.Machine;
import com.springboot.pjt1.data.entity.MachineData;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.MachineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MachineDataServiceImpl implements MachineDataService {
    private final MachineDataDAO machineDataDAO;
    private final MachineDAO machineDAO;

    @Autowired
    public MachineDataServiceImpl(MachineDataDAO machineDataDAO, MachineDAO machineDAO) {
        this.machineDataDAO = machineDataDAO;
        this.machineDAO = machineDAO;
    }
    @Override
    public MachineDataDTO getMachineData(long machineDataSeq) {
        MachineData machineData = machineDataDAO.SelectMachineDataById(machineDataSeq);
        MachineDataDTO machineDataDTO = new MachineDataDTO();

        machineDataDTO.setMachineDataSeq(machineData.getMachineDataSeq());
        machineDataDTO.setCreateTime(machineData.getCreateTime());
        machineDataDTO.setVideo(machineData.getVideo());
        machineDataDTO.setPost(machineData.getPost());
        machineDataDTO.setVoice(machineData.getVoice());
        machineDataDTO.setPhoto(machineData.getPhoto());

        return machineDataDTO;
    }

    @Override
    public MachineDataDTO insertMachineData(MachineDataInputDTO machineDataInputDTO) throws Exception {
        MachineData machineData = new MachineData();

        machineData.setMachineDataSeq(machineDataInputDTO.getMachineDataSeq());
        machineData.setMachineDataSeq(machineDataInputDTO.getMachineDataSeq());
        machineData.setCreateTime(new Date());
        machineData.setVideo(machineDataInputDTO.getVideo());
        machineData.setPost(machineDataInputDTO.getPost());
        machineData.setVoice(machineDataInputDTO.getVoice());
        machineData.setPhoto(machineDataInputDTO.getPhoto());

        MachineData savedMachineData = machineDataDAO.InsertMachineData(machineData);
        MachineDataDTO rMachineDataDTO = new MachineDataDTO();

        rMachineDataDTO.setMachineDataSeq(savedMachineData.getMachineDataSeq());
        rMachineDataDTO.setMachineDataSeq(savedMachineData.getMachineDataSeq());
        rMachineDataDTO.setCreateTime(savedMachineData.getCreateTime());
        rMachineDataDTO.setVideo(savedMachineData.getVideo());
        rMachineDataDTO.setPost(savedMachineData.getPost());
        rMachineDataDTO.setVoice(savedMachineData.getVoice());
        rMachineDataDTO.setPhoto(savedMachineData.getPhoto());

        return rMachineDataDTO;
    }

    @Override
    public void deleteMachineData(long machineDataSeq) throws Exception {
        machineDataDAO.DeleteMachineDataById(machineDataSeq);
    }
}
