package com.springboot.pjt1.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "MEMBER_SEQ")
    private long memberSeq;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column
    private String addrBase;
    @Column
    private String addrSpec;
    @Column
    private String memberProfile;
    @Column
    private String memberState;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column
    private String isAdmin;

    // mapping
    @OneToMany(mappedBy = "member")
    private List<Store> stores = new ArrayList<>();
    public void addStore(Store store){
        this.stores.add(store);

        if(store.getMember() != this)
            store.setMember(this);
    }

    @OneToMany(mappedBy = "member")
    private List<Feed> feeds = new ArrayList<>();
    public void addFeed(Feed feed){
        this.feeds.add(feed);

        if(feed.getMember() != this)
            feed.setMember(this);
    }

    @OneToMany(mappedBy = "member")
    private List<Notice> notices = new ArrayList<>();
    public void addNotice(Notice notice){
        this.notices.add(notice);

        if(notice.getMember() != this)
            notice.setMember(this);
    }

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();
    public void addComment(Comment comment){
        this.comments.add(comment);

        if(comment.getMember() != this)
            comment.setMember(this);
    }

    @OneToMany(mappedBy = "member")
    private List<Connect> connects = new ArrayList<>();

    public void addConnect(Connect connect){
        this.connects.add(connect);

        if(connect.getMember() != this)
            connect.setMember(this);
    }
}
