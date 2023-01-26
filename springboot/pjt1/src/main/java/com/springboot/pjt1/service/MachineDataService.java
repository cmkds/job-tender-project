package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MachineDataDTO;
import org.springframework.stereotype.Service;

public interface MachineDataService {
    MachineDataDTO getMachineData(long machineDataSeq);
    MachineDataDTO insertMachineData(MachineDataDTO machineDataDTO)throws Exception;
    MachineDataDTO updateMachineData(long machineDataSeq)throws Exception;
    void deleteMachineData(long machineDataSeq)throws Exception;
}
