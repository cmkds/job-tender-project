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
}
