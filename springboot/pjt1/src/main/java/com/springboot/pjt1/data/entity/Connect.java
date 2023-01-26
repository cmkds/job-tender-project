package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="connect")
public class Connect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long connectSeq;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column
    private long follower; // fk
    @Column
    private long following; // fk
}
