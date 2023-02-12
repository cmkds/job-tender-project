package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

import java.util.Date;

@Data
public class MemberInputDTO {
    private long memberSeq;
    private String name;
    private String email;
    private String nickname;
    private String addrBase;
    private String addrSpec;
    private String memberProfile;
    private String memberState;
    private String isAdmin;
}
