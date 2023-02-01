package com.springboot.pjt1.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HeartDTO {
    private long heartSeq;
    private Date createTime;
    private long feedSeq; //
    private long memberSeq; //
}
