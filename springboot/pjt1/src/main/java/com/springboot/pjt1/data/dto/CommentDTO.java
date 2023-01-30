package com.springboot.pjt1.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private long commentSeq;
    private String content;
    private Date modifyTime;
    private Date createTime;
    private long feedSeq; //
    private long memberSeq; //
}
