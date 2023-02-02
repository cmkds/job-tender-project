package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MachineDTO;
import org.springframework.stereotype.Service;

public interface MachineService {
    MachineDTO getMachine(long machineSeq);
    MachineDTO insertMachine(MachineDTO machineDTO)throws Exception;
    MachineDTO updateMachine(long machineSeq, String name, String address, String photo)throws Exception;
    void deleteMachine(long machineSeq)throws Exception;
}
