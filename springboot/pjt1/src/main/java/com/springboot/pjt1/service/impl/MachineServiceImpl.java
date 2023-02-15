package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MachineDAO;
import com.springboot.pjt1.data.dao.MachineLocationDAO;
import com.springboot.pjt1.data.dto.MachineDTO;
import com.springboot.pjt1.data.dto.custom.MachineInputDTO;
import com.springboot.pjt1.data.entity.Machine;
import com.springboot.pjt1.service.MachineService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MachineServiceImpl implements MachineService {
    private final MachineDAO machineDAO;
    private final MachineLocationDAO machineLocationDAO;

    public MachineServiceImpl(MachineDAO machineDAO, MachineLocationDAO machineLocationDAO) {
        this.machineDAO = machineDAO;
        this.machineLocationDAO = machineLocationDAO;
    }
    @Override
    public MachineDTO getMachine(long machineSeq) {
        Machine machine = machineDAO.SelectMachineById(machineSeq);
        MachineDTO machineDTO = new MachineDTO();

        machineDTO.setMachineSeq(machine.getMachineSeq());
        machineDTO.setCreateTime(machine.getCreateTime());
        machineDTO.setRecentTime(machine.getRecentTime());
        machineDTO.setMachineLocationSeq(machine.getMachineLocationSeq());
        machineDTO.setName(machine.getName());
        machineDTO.setAddress(machine.getAddress());
        machineDTO.setPhoto(machine.getPhoto());

        return machineDTO;
    }

    @Override
    public MachineDTO insertMachine(MachineInputDTO machineInputDTO) throws Exception {
        Machine machine = new Machine();

        machine.setMachineSeq(machineInputDTO.getMachineSeq());
        machine.setCreateTime(new Date());
        machine.setRecentTime(new Date());

        machine.setPhoto(machineInputDTO.getPhoto());
        machine.setAddress(machineInputDTO.getAddress());
        machine.setMachineLocationSeq(machineInputDTO.getMachineLocationSeq());
        machine.setName(machineInputDTO.getName());

        Machine savedMachine = machineDAO.InsertMachine(machine);
        MachineDTO rMachineDTO = new MachineDTO();

        rMachineDTO.setMachineSeq(savedMachine.getMachineSeq());
        rMachineDTO.setCreateTime(savedMachine.getCreateTime());
        rMachineDTO.setRecentTime(savedMachine.getRecentTime());

        rMachineDTO.setPhoto(savedMachine.getPhoto());
        rMachineDTO.setAddress(savedMachine.getAddress());
        rMachineDTO.setMachineLocationSeq(savedMachine.getMachineLocationSeq());
        rMachineDTO.setName(savedMachine.getName());

        return rMachineDTO;
    }

    @Override
    public MachineDTO updateMachine(long machineSeq, String name, String address, String photo) throws Exception {
        Machine updatedMachine = machineDAO.UpdateMachineById(machineSeq, name, address, photo);
        MachineDTO rMachineDTO = new MachineDTO();

        rMachineDTO.setMachineSeq(updatedMachine.getMachineSeq());
        rMachineDTO.setCreateTime(updatedMachine.getCreateTime());
        rMachineDTO.setRecentTime(updatedMachine.getRecentTime());

        return rMachineDTO;
    }

    @Override
    public void deleteMachine(long machineSeq) throws Exception {
        machineDAO.DeleteMachineById(machineSeq);
    }

    @Override
    public MachineDTO getMachineByMachineSeq(long machineSeq) {
        return null;
    }

}
