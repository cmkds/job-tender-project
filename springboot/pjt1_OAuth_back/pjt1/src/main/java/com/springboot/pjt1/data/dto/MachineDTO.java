package com.springboot.pjt1.data.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class MachineDTO {
    private long machineSeq;
    private String name;
    private String address;
    private String photo;
    private Date recentTime;
    private Date createTime;
    private long machineLocationSeq;
}
