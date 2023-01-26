package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "machine_location")
public class MachineLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long locSeq;
    @Column
    String city;
    @Column
    String name;
}
