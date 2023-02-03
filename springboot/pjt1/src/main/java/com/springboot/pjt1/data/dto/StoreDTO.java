package com.springboot.pjt1.data.dto;

import com.springboot.pjt1.data.entity.Member;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class StoreDTO {
    private long storeSeq;
    private String photo;
    private String video;
    private String post;
    private String voice;
    private Date createTime;
    private Date recentTime;
    private long memberSeq;
    private long machineLocationSeq;
}
