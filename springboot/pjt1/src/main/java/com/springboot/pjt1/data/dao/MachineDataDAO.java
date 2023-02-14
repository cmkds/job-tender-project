package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.dto.MachineDataDTO;
import com.springboot.pjt1.data.entity.MachineData;

public interface MachineDataDAO {
    MachineData InsertMachineData(MachineData machineData) throws Exception;
    MachineData SelectMachineDataById(long machineDataSeq);
    void DeleteMachineDataById(long machineDataSeq) throws Exception;
    MachineData SelectMachineDataSeqRecent();
}
