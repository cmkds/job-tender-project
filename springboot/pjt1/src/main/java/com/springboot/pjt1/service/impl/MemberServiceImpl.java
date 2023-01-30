package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
    @Override
    public MemberDTO getMember(long memberSeq) {
        Member member = memberDAO.SelectMemberById(memberSeq);
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberProfile(member.getMemberProfile());
        memberDTO.setMemberState(member.getMemberState());
        memberDTO.setMemberSeq(member.getMemberSeq());
        memberDTO.setCreateTime(new Date());
        memberDTO.setAddrSpec(member.getAddrSpec());
        memberDTO.setAddrBase(member.getAddrBase());
        memberDTO.setNickname(member.getNickname());
        memberDTO.setIsAdmin(member.getIsAdmin());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setModifyTime(member.getModifyTime());
        memberDTO.setName(member.getName());

        return memberDTO;
    }

    public List<MemberDTO> getMembers(){
        List<Member> members = memberDAO.SelectMemberAll();
        List<MemberDTO> memberDTOs = new ArrayList<>();

        for (int i = 0; i < members.size(); i++) {
            MemberDTO memberDTO = new MemberDTO();

            memberDTO.setMemberProfile(members.get(i).getMemberProfile());
            memberDTO.setMemberState(members.get(i).getMemberState());
            memberDTO.setMemberSeq(members.get(i).getMemberSeq());
            memberDTO.setCreateTime(new Date());
            memberDTO.setAddrSpec(members.get(i).getAddrSpec());
            memberDTO.setAddrBase(members.get(i).getAddrBase());
            memberDTO.setNickname(members.get(i).getNickname());
            memberDTO.setIsAdmin(members.get(i).getIsAdmin());
            memberDTO.setEmail(members.get(i).getEmail());
            memberDTO.setModifyTime(members.get(i).getModifyTime());
            memberDTO.setName(members.get(i).getName());

            memberDTOs.add(memberDTO);
        }

        return memberDTOs;
    }

    @Override
    public MemberDTO insertMember(MemberDTO memberDTO) throws Exception {
        Member member = new Member();

        System.out.println(memberDTO.getIsAdmin());
        member.setMemberProfile(memberDTO.getMemberProfile());
        member.setMemberState(memberDTO.getMemberState());
        member.setMemberSeq(memberDTO.getMemberSeq());
        member.setCreateTime(memberDTO.getCreateTime());
        member.setAddrSpec(memberDTO.getAddrSpec());
        member.setAddrBase(memberDTO.getAddrBase());
        member.setNickname(memberDTO.getNickname());
        member.setIsAdmin(memberDTO.getIsAdmin());
        member.setEmail(memberDTO.getEmail());
        member.setModifyTime(memberDTO.getModifyTime());
        member.setName(memberDTO.getName());

        Member savedMember = memberDAO.InsertMember(member);
        MemberDTO rMemberDTO = new MemberDTO();

        rMemberDTO.setMemberProfile(savedMember.getMemberProfile());
        rMemberDTO.setMemberState(savedMember.getMemberState());
        rMemberDTO.setMemberSeq(savedMember.getMemberSeq());
        rMemberDTO.setCreateTime(savedMember.getCreateTime());
        rMemberDTO.setAddrSpec(savedMember.getAddrSpec());
        rMemberDTO.setAddrBase(savedMember.getAddrBase());
        rMemberDTO.setNickname(savedMember.getNickname());
        rMemberDTO.setIsAdmin(savedMember.getIsAdmin());
        rMemberDTO.setEmail(savedMember.getEmail());
        rMemberDTO.setModifyTime(savedMember.getModifyTime());
        rMemberDTO.setName(savedMember.getName());

        return rMemberDTO;
    }

    @Override
    public MemberDTO updateMember(long memberSeq, String nickname, String addr_base, String addr_spec, String member_profile, String member_state, String is_admin) throws Exception {

        Member updatedMember = memberDAO.UpdateMemberById(memberSeq, nickname, addr_base, addr_spec, member_profile, member_state, is_admin);
        MemberDTO rMemberDTO = new MemberDTO();

        rMemberDTO.setMemberProfile(updatedMember.getMemberProfile());
        rMemberDTO.setMemberState(updatedMember.getMemberState());
        rMemberDTO.setMemberSeq(updatedMember.getMemberSeq());
        rMemberDTO.setCreateTime(updatedMember.getCreateTime());
        rMemberDTO.setAddrSpec(updatedMember.getAddrSpec());
        rMemberDTO.setAddrBase(updatedMember.getAddrBase());
        rMemberDTO.setNickname(updatedMember.getNickname());
        rMemberDTO.setIsAdmin(updatedMember.getIsAdmin());
        rMemberDTO.setEmail(updatedMember.getEmail());
        rMemberDTO.setModifyTime(updatedMember.getModifyTime());
        rMemberDTO.setName(updatedMember.getName());

        return rMemberDTO;
    }

    @Override
    public void deleteMember(long memberSeq) throws Exception {
        memberDAO.DeleteMemberById(memberSeq);
    }
}
