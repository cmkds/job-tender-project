package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="heart")
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long heartSeq;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    // mapping
    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private Member member;

    public void setMember(Member member){
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "feedSeq")
    private Feed feed;

    public void setFeed(Feed feed){
        this.feed = feed;
    }
}
