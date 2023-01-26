package com.springboot.pjt1.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConnectDTO {
    private long connectSeq;
    private Date createTime;
    private long follower; // fk
    private long following; // fk
}
