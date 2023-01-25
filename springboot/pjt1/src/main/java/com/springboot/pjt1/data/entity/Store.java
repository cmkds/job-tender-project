package com.springboot.pjt1.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeSeq;
    @Column
    private String photo;
    @Column
    private String video;
    @Column
    private String post;
    @Column
    private String voice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column
    private long createSeq;
    @Temporal(TemporalType.TIMESTAMP)
    private Date recentTime;
    @Column
    private long recentSeq;
}
