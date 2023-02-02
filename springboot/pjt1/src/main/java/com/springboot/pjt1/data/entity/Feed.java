package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "feed")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="FEED_SEQ")
    private long feedSeq;
    @Column
    private String content;
    @Column(nullable = false)
    private String post;
    @Column(nullable = false)
    private int heart;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifyTime;

    // mapping
    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private Member member;

    public void setMember(Member member){
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "machineLocationSeq")
    private MachineLocation machineLocation;

    public void setMachineLocation(MachineLocation machineLocation){
        this.machineLocation = machineLocation;
    }
}
