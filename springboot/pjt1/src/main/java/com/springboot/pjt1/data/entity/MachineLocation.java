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
    long machineLocationSeq;
    @Column(nullable = false)
    String city;
    @Column(nullable = false)
    String name;

    // mapping


}
