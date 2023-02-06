package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

import java.util.Date;

@Data
public class FeedInputDTO {
    private long feedSeq;
    private String content;
    private String post;
    private long memberSeq;
    private long machineLocationSeq;
}
