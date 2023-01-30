package com.springboot.pjt1.data.entity;

import jdk.jfr.ContentType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "COMMENT_SEQ")
    private long commentSeq;
    @Column(nullable = false)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    // mapping
    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private Member member;

    public void setMember(Member member){
        this.member = member;

        if (!member.getComments().contains(this))
            member.getComments().add(this);
    }

    @ManyToOne
    @JoinColumn(name = "feedSeq")
    private Feed feed;

    public void setMember(Feed feed){
        this.feed = feed;

        if (!feed.getComments().contains(this))
            feed.getComments().add(this);
    }
}
