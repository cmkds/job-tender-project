package com.springboot.pjt1.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MachineDataDTO {
    private long machineDataSeq;
    private String photo;
    private String post;
    private String video;
    private String voice;
    private Date createTime;
    private long machineSeq;
}
