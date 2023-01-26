package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MachineLocationDTO;
import com.springboot.pjt1.data.entity.MachineLocation;
import org.springframework.stereotype.Service;

public interface MachineLocationService {
    MachineLocationDTO getMachineLocation(long machineLocationSeq);
    MachineLocationDTO insertMachineLocation(MachineLocationDTO machineLocationDTO)throws Exception;
    MachineLocationDTO updateMachineLocation(long machineLocationSeq, String city, String name)throws Exception;
    void deleteMachineLocation(long machineLocationSeq)throws Exception;
}
