package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.MachineDAO;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Machine;
import com.springboot.pjt1.data.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class MachineDAOImpl implements MachineDAO {
    private MachineRepository machineRepository;

    @Autowired
    public MachineDAOImpl(MachineRepository machineRepository){
        this.machineRepository = machineRepository;
    }
    @Override
    public Machine InsertMachine(Machine machine) throws Exception {
        Machine savedMachine = machineRepository.save(machine);

        return savedMachine;
    }

    @Override
    public Machine SelectMachineById(long machineSeq) {
        Machine selectedMachine = machineRepository.getById(machineSeq);

        return selectedMachine;
    }

    @Override
    public Machine UpdateMachineById(long machineSeq) throws Exception{
        Optional<Machine> selectedMachine = machineRepository.findById(machineSeq);
        Machine updatedMachine;

        if(selectedMachine.isPresent()){
            Machine Machine = selectedMachine.get();

            Machine.setRecentTime(new Date());

            updatedMachine = machineRepository.save(Machine);
        }

        else
            throw new Exception();

        return updatedMachine;
    }

    @Override
    public void DeleteMachineById(long machineSeq) throws Exception {
        Optional<Machine> selectedMachine = machineRepository.findById(machineSeq);

        if (selectedMachine.isPresent()){
            Machine machine = selectedMachine.get();
            machineRepository.delete(machine);
        }

        else
            throw new Exception();
    }


}
