package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

import java.util.Date;
@Data
public class StoreInputDTO {
    private long storeSeq;
    private String photo;
    private String video;
    private String post;
    private String voice;
    private long memberSeq;
    private long machineLocationSeq;
    private Date machineDataCreateTime;
}
