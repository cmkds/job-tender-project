package com.springboot.pjt1.data.dto;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {

    public MemberDTO toDto(OAuth2User oAuth2User) {

        var attributes = oAuth2User.getAttributes();
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName((String) attributes.get("name"));
        memberDTO.setEmail((String) attributes.get("email"));

        return memberDTO;
    }
}