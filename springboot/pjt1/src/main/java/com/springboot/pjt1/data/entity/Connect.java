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
    private long connectSeq;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    // mapping
    @ManyToOne
    @JoinColumn(name = "followerSeq")
    private Member follower;

    public void setFollower(Member follower){
        this.follower = follower;
    }

    @ManyToOne
    @JoinColumn(name = "followingSeq")
    private Member following;

    public void setFollowing(Member following){
        this.following = following;
    }
}
