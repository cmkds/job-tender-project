package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "NOTICE_SEQ")
    private long noticeSeq;
    @Column
    String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column
    private long createSeq;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column
    private long modifySeq;
    @Column
    private int hit;

    // mapping
    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private Member member;

    public void setMember(Member member){
        this.member = member;
    }
}
