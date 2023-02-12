package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "NOTICE_SEQ")
    private long noticeSeq;
    @Column
    String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;
    @Column
    private long createSeq;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifyTime;
    @Column
    private int hit;
    @Column(nullable = false)
    private long memberSeq;
}
