package com.springboot.pjt1.data.entity;

import jdk.jfr.ContentType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentSeq;
    @Column(nullable = false)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifyTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;
    @Column(nullable = false)
    private long feedSeq;
    @Column(nullable = false)
    private long memberSeq;
}
