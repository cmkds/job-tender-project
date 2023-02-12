package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

@Data
public class MemberInitDTO {
    long memberSeq;
    String nickname;
    String memberProfile;
    String memberState;
}
