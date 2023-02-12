package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long machineSeq;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column
    private String photo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date recentTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;
    @Column(nullable = false)
    private long machineLocationSeq;
}
