package com.springboot.pjt1.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeSeq;
    @Column
    String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column
    private long createSeq;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column
    private long modifySeq;
    @Column
    private int hit;
}
