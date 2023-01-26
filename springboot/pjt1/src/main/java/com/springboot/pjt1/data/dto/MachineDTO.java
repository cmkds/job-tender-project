package com.springboot.pjt1.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MachineDTO {
    private long machineSeq;
    private Date recentTime;
    private Date createTime;
    private long locSeq;
}
