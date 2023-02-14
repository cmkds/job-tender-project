package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.MachineDataDAO;
import com.springboot.pjt1.data.dto.MachineDataDTO;
import com.springboot.pjt1.data.entity.Machine;
import com.springboot.pjt1.data.entity.MachineData;
import com.springboot.pjt1.data.repository.MachineDataRepository;
import com.springboot.pjt1.data.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class MachineDataDAOImpl implements MachineDataDAO {
    private MachineDataRepository machineDataRepository;

    @Autowired
    public MachineDataDAOImpl(MachineDataRepository machineDataRepository){
        this.machineDataRepository = machineDataRepository;
    }
    @Override
    public MachineData InsertMachineData(MachineData machineData) throws Exception {
        MachineData savedMachineData = machineDataRepository.save(machineData);

        return savedMachineData;
    }

    @Override
    public MachineData SelectMachineDataById(long machineDataSeq) {
        MachineData selectedMachineData = machineDataRepository.getById(machineDataSeq);

        return selectedMachineData;
    }

    @Override
    public void DeleteMachineDataById(long machineDataSeq) throws Exception{
        Optional<MachineData> selectedMachineData = machineDataRepository.findById(machineDataSeq);

        if (selectedMachineData.isPresent()){
            MachineData MachineData = selectedMachineData.get();
            machineDataRepository.delete(MachineData);
        }

        else
            throw new Exception();
    }

    @Override
    public MachineData SelectMachineDataSeqRecent() {
        List<MachineData> MachineData = machineDataRepository.findAllByOrderByCreateTimeDesc();

        if (MachineData.size() == 0)
            return null;

        return MachineData.get(0);
    }
}
