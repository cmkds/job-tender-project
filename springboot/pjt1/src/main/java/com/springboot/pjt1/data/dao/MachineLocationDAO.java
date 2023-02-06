package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Machine;
import com.springboot.pjt1.data.entity.MachineLocation;

import java.util.List;

public interface MachineLocationDAO {
    MachineLocation InsertMachineLocation(MachineLocation machineLocation) throws Exception;
    MachineLocation SelectMachineLocationById(long locSeq);
    MachineLocation UpdateMachineLocationById(long locSeq, String city, String name) throws Exception;
    void DeleteMachineLocationById(long locSeq) throws Exception;
    boolean IsExistByMachineLocationSeq(long machineLocationSeq);
}
