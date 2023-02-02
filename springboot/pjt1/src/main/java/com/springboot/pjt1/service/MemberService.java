package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.dto.custom.MemberInitDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    MemberDTO getMember(long memberSeq);
    List<MemberDTO> getMembers();
    MemberDTO insertMember(MemberDTO memberDTO)throws Exception;
    MemberDTO updateMember(long memberSeq, String nickname, String addr_base, String addr_spec,
                           String member_profile, String member_state, String is_admin)throws Exception;
    MemberDTO updateMember(long memberSeq, MemberInitDTO memberInitDTO) throws Exception;
    void deleteMember(long memberSeq)throws Exception;
    //
    boolean findNickname(String nickname);
}
