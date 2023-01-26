package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="machine_data")
public class MachineData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long machineDataSeq;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false)
    private String post;
    @Column
    private String video;
    @Column
    private String voice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column
    private long machineSeq;
}
