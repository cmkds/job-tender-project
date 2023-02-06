package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MachineLocationDAO;
import com.springboot.pjt1.data.dto.MachineLocationDTO;
import com.springboot.pjt1.data.dto.custom.MachineLocationInputDTO;
import com.springboot.pjt1.data.entity.MachineLocation;
import com.springboot.pjt1.service.MachineLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineLocationServiceImpl implements MachineLocationService {
    private final MachineLocationDAO machineLocationDAO;

    @Autowired
    public MachineLocationServiceImpl(MachineLocationDAO machineLocationDAO) {
        this.machineLocationDAO = machineLocationDAO;
    }
    @Override
    public MachineLocationDTO getMachineLocation(long machineLocationSeq) {
        MachineLocation MachineLocation = machineLocationDAO.SelectMachineLocationById(machineLocationSeq);
        MachineLocationDTO machineLocationDTO = new MachineLocationDTO();

        machineLocationDTO.setMachineLocationSeq(MachineLocation.getMachineLocationSeq());
        machineLocationDTO.setCity(MachineLocation.getCity());
        machineLocationDTO.setName(MachineLocation.getName());

        return machineLocationDTO;
    }

    @Override
    public boolean IsExistByMachineLocationSeq(long machineLocationSeq) {
        return machineLocationDAO.IsExistByMachineLocationSeq(machineLocationSeq);
    }

    @Override
    public MachineLocationDTO insertMachineLocation(MachineLocationInputDTO machineLocationInputDTO) throws Exception {
        MachineLocation MachineLocation = new MachineLocation();

        MachineLocation.setMachineLocationSeq(machineLocationInputDTO.getMachineLocationSeq());
        MachineLocation.setCity(machineLocationInputDTO.getCity());
        MachineLocation.setName(machineLocationInputDTO.getName());

        MachineLocation savedMachineLocation = machineLocationDAO.InsertMachineLocation(MachineLocation);
        MachineLocationDTO rMachineLocationDTO = new MachineLocationDTO();

        rMachineLocationDTO.setMachineLocationSeq(savedMachineLocation.getMachineLocationSeq());
        rMachineLocationDTO.setCity(savedMachineLocation.getCity());
        rMachineLocationDTO.setName(savedMachineLocation.getName());

        return rMachineLocationDTO;
    }

    @Override
    public MachineLocationDTO updateMachineLocation(long machineLocationSeq, String city, String name) throws Exception {
        MachineLocation updatedMachineLocation = machineLocationDAO.UpdateMachineLocationById(machineLocationSeq, city, name);
        MachineLocationDTO rMachineLocationDTO = new MachineLocationDTO();

        rMachineLocationDTO.setMachineLocationSeq(updatedMachineLocation.getMachineLocationSeq());
        rMachineLocationDTO.setCity(updatedMachineLocation.getCity());
        rMachineLocationDTO.setName(updatedMachineLocation.getName());

        return rMachineLocationDTO;
    }

    @Override
    public void deleteMachineLocation(long machineLocationSeq) throws Exception {
        machineLocationDAO.DeleteMachineLocationById(machineLocationSeq);
    }
}
