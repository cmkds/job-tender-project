package com.springboot.pjt1.data.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class FeedDTO {
    private long feedSeq;
    @Column
    private long memberSeq;
    @Column
    private String content;
    private String post;
    private int heart;
    private Date createTime;
    private Date modifyTime;
}
