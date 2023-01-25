package com.springboot.pjt1.data.entity;

import jdk.jfr.ContentType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long commentSeq;
    @Column(nullable = false)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(nullable = false)
    private long feedSeq; //
    @Column(nullable = false)
    private long ownerSeq; //
}
