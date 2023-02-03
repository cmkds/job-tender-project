package com.springboot.pjt1.data.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MachineLocationDTO {
    long machineLocationSeq;
    String city;
    String name;
}
