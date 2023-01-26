package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MemberDTO;
import org.springframework.stereotype.Service;

public interface MemberService {
    MemberDTO getMember(long memberSeq);
    MemberDTO insertMember(MemberDTO memberDTO)throws Exception;
    MemberDTO updateMember(long memberSeq, String nickname, String addr_base, String addr_spec,
                           String member_profile, String member_state, String is_admin)throws Exception;
    void deleteMember(long memberSeq)throws Exception;
}
