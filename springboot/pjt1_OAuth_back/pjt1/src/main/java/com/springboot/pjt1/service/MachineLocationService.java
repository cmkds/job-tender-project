package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MachineLocationDTO;
import com.springboot.pjt1.data.dto.custom.MachineLocationInputDTO;
import com.springboot.pjt1.data.entity.MachineLocation;
import org.springframework.stereotype.Service;

public interface MachineLocationService {
    MachineLocationDTO getMachineLocation(long machineLocationSeq);
    boolean IsExistByMachineLocationSeq(long machineLocationSeq);
    MachineLocationDTO insertMachineLocation(MachineLocationInputDTO machineLocationinputDTO)throws Exception;
    MachineLocationDTO updateMachineLocation(long machineLocationSeq, String city, String name)throws Exception;
    void deleteMachineLocation(long machineLocationSeq)throws Exception;
}
