package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "machine_location")
public class MachineLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MACHINE_LOCATION_SEQ")
    long locSeq;
    @Column
    String city;
    @Column
    String name;

    // mapping
    @OneToOne(mappedBy = "machineLocation")
    private Machine machine;
    public void addMachine(Machine machine){
        this.machine = machine;

        if(machine.getMachineLocation() != this)
            machine.setMachineLocation(this);
    }


}
