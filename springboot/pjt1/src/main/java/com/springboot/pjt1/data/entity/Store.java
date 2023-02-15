package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="STORE_SEQ")
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
    @Column(nullable = false)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date recentTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date machineDataCreateTime;
    @Column(nullable = false)
    private long memberSeq;
    @Column(nullable = false)
    private long machineLocationSeq;
}
