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
    private long machineSeq;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date recentTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    @OneToOne
    @JoinColumn(name = "MACHINE_LOCATION_SEQ")
    private MachineLocation machineLocation;

    public void setMachineLocation(MachineLocation machineLocation){
        this.machineLocation = machineLocation;
    }
}
