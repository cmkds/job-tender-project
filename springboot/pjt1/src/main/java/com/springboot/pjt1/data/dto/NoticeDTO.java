package com.springboot.pjt1.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDTO {
    private long noticeSeq;
    String content;
    private Date createTime;
    private long createSeq;
    private Date modifyTime;
    private long modifySeq;
    private int hit;

    private long memberSeq;
}
