package com.springboot.pjt1.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class MemberDTO {
    private long memberSeq;
    private String name;
    private String email;
    private String nickname;
    private String addrBase;
    private String addrSpec;
    private String memberProfile;
    private String memberState;
    private Date createTime;
    private Date modifyTime;
    private String isAdmin;

}