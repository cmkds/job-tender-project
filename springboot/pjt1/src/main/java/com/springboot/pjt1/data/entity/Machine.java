package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "MACHINE_SEQ")
    private long machineSeq;
    @Temporal(TemporalType.TIMESTAMP)
    private Date recentTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column
    private long locSeq;

    @OneToOne
    @JoinColumn(name = "MACHINE_LOCATION_SEQ")
    private MachineLocation machineLocation;

    public void setMachineLocation(MachineLocation machineLocation){
        this.machineLocation = machineLocation;

        if (!(machineLocation.getMachine() == this))
            machineLocation.setMachine(this);
    }

    @OneToMany(mappedBy = "machine")
    private List<MachineData> machineDatas = new ArrayList<>();

    public void setMachineData(MachineData machineData){
        this.machineDatas.add(machineData);

        if(machineData.getMachine() != this)
            machineData.setMachine(this);
    }
}
