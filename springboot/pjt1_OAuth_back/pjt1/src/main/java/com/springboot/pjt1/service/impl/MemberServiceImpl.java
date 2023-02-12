package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.dto.custom.MemberInitDTO;
import com.springboot.pjt1.data.dto.custom.MemberInputDTO;
import com.springboot.pjt1.data.dto.custom.MemberSearchInfoDTO;
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
        for(Member member : members){
            MemberDTO memberDTO = new MemberDTO();

            memberDTO.setMemberProfile(member.getMemberProfile());
            memberDTO.setMemberState(member.getMemberState());
            memberDTO.setMemberSeq(member.getMemberSeq());
            memberDTO.setCreateTime(member.getCreateTime());
            memberDTO.setAddrSpec(member.getAddrSpec());
            memberDTO.setAddrBase(member.getAddrBase());
            memberDTO.setNickname(member.getNickname());
            memberDTO.setIsAdmin(member.getIsAdmin());
            memberDTO.setEmail(member.getEmail());
            memberDTO.setModifyTime(member.getModifyTime());
            memberDTO.setName(member.getName());

            memberDTOs.add(memberDTO);
        }



        return memberDTOs;
    }

    @Override
    public List<MemberSearchInfoDTO> getMemberSearchInfo(String nickname) {
        List<Member> members = memberDAO.SelectMemberAll();
        List<MemberSearchInfoDTO> memberSearchInfoDTOs = new ArrayList<>();



        for (Member member: members){
            MemberSearchInfoDTO memberSearchInfoDTO = new MemberSearchInfoDTO();

            memberSearchInfoDTO.setMemberProfile(member.getMemberProfile());
            memberSearchInfoDTO.setMemberSeq(member.getMemberSeq());
            memberSearchInfoDTO.setNickname(member.getNickname());

            memberSearchInfoDTOs.add(memberSearchInfoDTO);
        }

        return memberSearchInfoDTOs;
    }

    @Override
    public List<MemberSearchInfoDTO> getMemberSearchFollowingInfo(long memberSeq) {
        return null;
    }

    @Override
    public MemberDTO insertMember(MemberInputDTO memberInputDTO) throws Exception {
        Member member = new Member();

        member.setMemberProfile(memberInputDTO.getMemberProfile());
        member.setMemberState(memberInputDTO.getMemberState());
        member.setMemberSeq(memberInputDTO.getMemberSeq());
        member.setCreateTime(new Date());
        member.setAddrSpec(memberInputDTO.getAddrSpec());
        member.setAddrBase(memberInputDTO.getAddrBase());
        member.setNickname(memberInputDTO.getNickname());
        member.setIsAdmin(memberInputDTO.getIsAdmin());
        member.setEmail(memberInputDTO.getEmail());
        member.setModifyTime(new Date());
        member.setName(memberInputDTO.getName());

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
    public List<MemberDTO> insertMembers(List<MemberInputDTO> memberInputDTO) throws Exception {
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < memberInputDTO.size(); i++) {
            Member member = new Member();

            member.setMemberProfile(memberInputDTO.get(i).getMemberProfile());
            member.setMemberState(memberInputDTO.get(i).getMemberState());
            member.setMemberSeq(memberInputDTO.get(i).getMemberSeq());
            member.setCreateTime(new Date());
            member.setAddrSpec(memberInputDTO.get(i).getAddrSpec());
            member.setAddrBase(memberInputDTO.get(i).getAddrBase());
            member.setNickname(memberInputDTO.get(i).getNickname());
            member.setIsAdmin(memberInputDTO.get(i).getIsAdmin());
            member.setEmail(memberInputDTO.get(i).getEmail());
            member.setModifyTime(new Date());
            member.setName(memberInputDTO.get(i).getName());

            members.add(member);
        }

        List<Member> savedMembers = memberDAO.InsertMembers(members);
        List<MemberDTO> rMemberDTOs = new ArrayList<>();
        for (Member savedMember : savedMembers) {
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

            rMemberDTOs.add(rMemberDTO);
        }
        return rMemberDTOs;
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

    public MemberDTO updateMember(long memberSeq, MemberInitDTO memberInitDTO) throws Exception {

        Member updatedMember = memberDAO.UpdateMemberById(memberSeq, memberInitDTO);
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

    @Override
    public boolean findNickname(String nickname) {
        return memberDAO.findByNickname(nickname);
    }

    @Override
    public MemberSearchInfoDTO getMemberSearchInfoByMemberSeq(Long list) {
        Member member = memberDAO.SelectMemberById(list);
        MemberSearchInfoDTO memberSearchInfoDTO = new MemberSearchInfoDTO();

        memberSearchInfoDTO.setNickname(member.getNickname());
        memberSearchInfoDTO.setMemberSeq(member.getMemberSeq());
        memberSearchInfoDTO.setMemberProfile(member.getMemberProfile());

        return memberSearchInfoDTO;
    }

    @Override
    public boolean findMemberByEmailReturnBool(String email) {
        return memberDAO.findMemberByEmailReturnBool(email);
    }


}
