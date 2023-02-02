package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.dto.custom.MemberInitDTO;
import com.springboot.pjt1.data.entity.Member;

import java.util.List;

public interface MemberDAO {
    Member InsertMember(Member member) throws Exception;
    Member SelectMemberById(long memberSeq);
    List<Member> SelectMemberAll();
    Member UpdateMemberById(long memberSeq, String nickname, String addr_base, String addr_spec,
                            String member_profile, String member_state, String is_admin) throws Exception;
    Member UpdateMemberById(long memberSeq, MemberInitDTO memberInitDTO) throws Exception;

    void DeleteMemberById(long memberSeq) throws Exception;
    //
    boolean findByNickname(String nickname);

}
