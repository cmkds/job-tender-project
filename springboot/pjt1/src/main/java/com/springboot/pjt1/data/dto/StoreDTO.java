package com.springboot.pjt1.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StoreDTO {
    private long storeSeq;
    private String photo;
    private String video;
    private String post;
    private String voice;
    private Date createTime;
    private long createSeq;
    private Date recentTime;
    private long recentSeq;
}
