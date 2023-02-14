package com.springboot.pjt1.data.entity;

import com.springboot.pjt1.data.dto.Role;
import lombok.Builder;
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
    @Column(nullable = false)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifyTime;
    @Column(nullable = false)
    private String isAdmin;
    @Column(name = "provider")
    private String provider;

    @Builder //생성을 Builder 패턴으로 하기 위해서
    public Member(String name, String email, String provider, String nickname, String isAdmin) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
        this.isAdmin = isAdmin;
        this.createTime = new Date();
        this.modifyTime = new Date();

    }

    public Member(){

    }


    public Member update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }
}
