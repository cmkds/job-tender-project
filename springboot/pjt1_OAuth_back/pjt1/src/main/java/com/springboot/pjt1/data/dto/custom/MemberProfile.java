package com.springboot.pjt1.data.dto.custom;


import com.springboot.pjt1.data.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class MemberProfile {
    private String name;
    private String email;
    private String provider;
    private String nickname;
    //
    private Date createTime;
    private Date modifyTime;
    private String isAdmin;

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .isAdmin(isAdmin)
                .nickname(nickname)
                .build();
    }

}