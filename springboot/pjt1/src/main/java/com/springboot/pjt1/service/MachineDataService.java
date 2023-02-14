package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MachineDataDTO;
import com.springboot.pjt1.data.dto.custom.MachineDataInputDTO;
import org.springframework.stereotype.Service;

public interface MachineDataService {
    MachineDataDTO getMachineData(long machineDataSeq);
    MachineDataDTO insertMachineData(MachineDataInputDTO machineDataInputDTO)throws Exception;
    void deleteMachineData(long machineDataSeq)throws Exception;

    MachineDataDTO getRecentMachineData();
}
