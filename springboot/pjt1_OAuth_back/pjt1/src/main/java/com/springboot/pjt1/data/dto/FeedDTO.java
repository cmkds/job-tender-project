package com.springboot.pjt1.data.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class FeedDTO {
    private long feedSeq;
    private String content;
    private String post;
    private int heart;
    private Date createTime;
    private Date modifyTime;
    private long memberSeq;
    private long machineLocationSeq;
}
