package com.springboot.pjt1.data.dto;

import lombok.Builder;
import lombok.Data;
import net.minidev.json.JSONObject;

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
    private JSONObject response;

    public MemberDTO(){}
    @Builder
    public MemberDTO(String email, String name){
        this.name = name;
        this.email = email;
        this.nickname = "empty";
        this.isAdmin = "Y";
        this.createTime = new Date();
        this.modifyTime = new Date();
    }
}
