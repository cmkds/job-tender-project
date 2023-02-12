package com.springboot.pjt1.data.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class NoticeDTO {
    private long noticeSeq;
    String content;
    private Date createTime;
    private long createSeq;
    private Date modifyTime;
    private int hit;
    private long memberSeq;
}
