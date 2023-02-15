package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.MachineLocationDAO;
import com.springboot.pjt1.data.dto.MachineLocationDTO;
import com.springboot.pjt1.data.entity.MachineLocation;
import com.springboot.pjt1.data.entity.MachineLocation;
import com.springboot.pjt1.data.repository.MachineLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class MachineLocationDAOImpl implements MachineLocationDAO {
    private MachineLocationRepository machineLocationRepository;

    @Autowired
    public MachineLocationDAOImpl(MachineLocationRepository machineLocationRepository){
        this.machineLocationRepository = machineLocationRepository;
    }
    @Override
    public MachineLocation InsertMachineLocation(MachineLocation machineLocation) throws Exception {
        MachineLocation savedMachineLocation = machineLocationRepository.save(machineLocation);

        return savedMachineLocation;
    }

    @Override
    public MachineLocation SelectMachineLocationById(long locSeq) {
        MachineLocation selectedMachineLocation = machineLocationRepository.getById(locSeq);

        return selectedMachineLocation;
    }

    @Override
    public MachineLocation UpdateMachineLocationById(long locSeq, String city, String name) throws Exception {
        // get data using ID
        Optional<MachineLocation> selectedMachineLocation = machineLocationRepository.findById(locSeq);
        MachineLocation updatedMachineLocation;

        if(selectedMachineLocation.isPresent()){
            MachineLocation machineLocation = selectedMachineLocation.get();

            machineLocation.setCity(city);
            machineLocation.setName(name);

            updatedMachineLocation = machineLocationRepository.save(machineLocation);
        }

        else
            throw new Exception();

        return updatedMachineLocation;
    }

    @Override
    public void DeleteMachineLocationById(long locSeq) throws Exception {
        Optional<MachineLocation> selectedMachineLocation = machineLocationRepository.findById(locSeq);

        if (selectedMachineLocation.isPresent()){
            MachineLocation machineLocation = selectedMachineLocation.get();
            machineLocationRepository.delete(machineLocation);
        }

        else
            throw new Exception();
    }

    @Override
    public boolean IsExistByMachineLocationSeq(long machineLocationSeq) {
        return machineLocationRepository.existsById(machineLocationSeq);
    }

    @Override
    public MachineLocationDTO SelectMachineLocationByMachineDataSeq(long machineDataSeq) {
        return null;
    }
}
