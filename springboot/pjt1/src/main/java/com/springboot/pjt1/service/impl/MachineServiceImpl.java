package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MachineDAO;
import com.springboot.pjt1.data.dao.MachineLocationDAO;
import com.springboot.pjt1.data.dto.MachineDTO;
import com.springboot.pjt1.data.dto.MachineDTO;
import com.springboot.pjt1.data.entity.Machine;
import com.springboot.pjt1.data.entity.MachineLocation;
import com.springboot.pjt1.service.MachineService;
import org.springframework.stereotype.Service;

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
        machineDTO.setLocSeq(machine.getLocSeq());
        machineDTO.setRecentTime(machine.getRecentTime());

        return machineDTO;
    }

    @Override
    public MachineDTO insertMachine(MachineDTO machineDTO) throws Exception {
        Machine machine = new Machine();

        machine.setMachineSeq(machineDTO.getMachineSeq());
        machine.setCreateTime(machineDTO.getCreateTime());
        machine.setLocSeq(machineDTO.getLocSeq());
        machine.setRecentTime(machineDTO.getRecentTime());

        MachineLocation loc_mac = machineLocationDAO.SelectMachineLocationById(machine.getLocSeq());
        machine.setMachineLocation(loc_mac);

        Machine savedMachine = machineDAO.InsertMachine(machine);
        MachineDTO rMachineDTO = new MachineDTO();

        rMachineDTO.setMachineSeq(savedMachine.getMachineSeq());
        rMachineDTO.setCreateTime(savedMachine.getCreateTime());
        rMachineDTO.setLocSeq(savedMachine.getLocSeq());
        rMachineDTO.setRecentTime(savedMachine.getRecentTime());

        return rMachineDTO;
    }

    @Override
    public MachineDTO updateMachine(long machineSeq) throws Exception {
        Machine updatedMachine = machineDAO.UpdateMachineById(machineSeq);
        MachineDTO rMachineDTO = new MachineDTO();

        rMachineDTO.setMachineSeq(updatedMachine.getMachineSeq());
        rMachineDTO.setCreateTime(updatedMachine.getCreateTime());
        rMachineDTO.setLocSeq(updatedMachine.getLocSeq());
        rMachineDTO.setRecentTime(updatedMachine.getRecentTime());

        return rMachineDTO;
    }

    @Override
    public void deleteMachine(long machineSeq) throws Exception {
        machineDAO.DeleteMachineById(machineSeq);
    }
}
