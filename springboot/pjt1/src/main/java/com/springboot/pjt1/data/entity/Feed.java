package com.springboot.pjt1.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feed")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedSeq;
    @Column
    private long memberSeq;
    @Column
    private String content;
    @Column(nullable = false)
    private String post;
    @Column(nullable = false)
    private int like;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
}
