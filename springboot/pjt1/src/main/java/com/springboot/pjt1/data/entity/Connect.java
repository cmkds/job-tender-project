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
//    @Column(name = "CONNECT_SEQ")
    private long connectSeq;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    // mapping
    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private Member follower;

    public void setFollower(Member follower){
        this.follower = follower;
    }

    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private Member following;

    public void setFollowing(Member following){
        this.following = following;
    }
}
