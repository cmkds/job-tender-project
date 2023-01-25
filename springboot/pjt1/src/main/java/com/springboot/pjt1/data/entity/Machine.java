package com.springboot.pjt1.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long machineSeq;
    @Temporal(TemporalType.TIMESTAMP)
    private Date recentTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column
    private long locSeq;
}
