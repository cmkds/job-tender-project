package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Machine;

import java.util.List;

public interface MachineDAO {
    Machine InsertMachine(Machine machine) throws Exception;
    Machine SelectMachineById(long machineSeq);
    Machine UpdateMachineById(long machineSeq, String name, String address, String photo) throws Exception;
    void DeleteMachineById(long machineSeq) throws Exception;

}
