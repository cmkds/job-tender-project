package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

import java.util.Date;
@Data
public class MachineDataInputDTO {
    private long machineDataSeq;
    private String photo;
    private String post;
    private String video;
    private String voice;
    private long MachineSeq;
}
