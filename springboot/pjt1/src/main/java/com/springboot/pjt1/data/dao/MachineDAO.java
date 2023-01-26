package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Machine;

import java.util.List;

public interface MachineDAO {
    Machine InsertMachine(Machine machine) throws Exception;
    Machine SelectMachineById(long machineSeq);
    Machine UpdateMachineById(long machineSeq) throws Exception;
    void DeleteMachineById(long machineSeq) throws Exception;

}
