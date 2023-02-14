package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.dto.custom.MemberInitDTO;
import com.springboot.pjt1.data.dto.custom.MemberInputDTO;
import com.springboot.pjt1.data.dto.custom.MemberSearchInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    MemberDTO getMember(long memberSeq);
    List<MemberDTO> getMembers();
    List<MemberSearchInfoDTO> getMemberSearchInfo(String nickname);
    List<MemberSearchInfoDTO> getMemberSearchFollowingInfo(long memberSeq);
    MemberDTO insertMember(MemberInputDTO memberInputDTO)throws Exception;
    List<MemberDTO> insertMembers(List<MemberInputDTO> memberInputDTO) throws Exception;
    MemberDTO updateMember(long memberSeq, String nickname, String addr_base, String addr_spec,
                           String member_profile, String member_state, String is_admin) throws Exception;
    MemberDTO updateMember(long memberSeq, MemberInitDTO memberInitDTO) throws Exception;
    void deleteMember(long memberSeq)throws Exception;
    //
    boolean findNickname(String nickname);

    MemberSearchInfoDTO getMemberSearchInfoByMemberSeq(Long list);
    boolean findMemberByEmailReturnBool(String email);

    List<MemberDTO> getMemberAll();

    MemberDTO insertMemberByNaver(MemberInputDTO memberInputDTO) throws Exception;

    MemberDTO findMemberByEmail(String email);
}
