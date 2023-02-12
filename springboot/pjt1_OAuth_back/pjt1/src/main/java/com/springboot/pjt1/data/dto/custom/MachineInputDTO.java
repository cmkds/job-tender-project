package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

@Data
public class MachineInputDTO {
    private long machineSeq;
    private String name;
    private String address;
    private String photo;
    private long machineLocationSeq;
}
