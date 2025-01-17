package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.custom.MemberInitDTO;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Component
public class MemberDAOImpl implements MemberDAO {
    private MemberRepository memberRepository;
    @Autowired
    public MemberDAOImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Override
    public Member InsertMember(Member member) throws Exception {
        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    @Override
    public Member SelectMemberById(long memberSeq) {
        Member selectedMember = memberRepository.getById(memberSeq);

        return selectedMember;
    }

    public List<Member> SelectMemberAll(){
        List<Member> selectedMembers = memberRepository.findAll();

        return selectedMembers;
    }

    @Override
    public Member UpdateMemberById(long memberSeq, String nickname, String addr_base, String addr_spec, String member_profile, String member_state, String is_admin) throws Exception {
        // get data using ID
        Optional<Member> selectedMember = memberRepository.findById(memberSeq);
        Member updatedMember;

        if(selectedMember.isPresent()){
            Member member = selectedMember.get();

            member.setNickname(nickname);
            member.setAddrBase(addr_base);
            member.setAddrSpec(addr_spec);
            member.setMemberProfile(member_profile);
            member.setMemberState(member_state);
            member.setIsAdmin(is_admin);
            member.setModifyTime(new Date());

            updatedMember = memberRepository.save(member);
        }

        else
            throw new Exception();

        return updatedMember;
    }

    @Override
    public Member UpdateMemberById(long memberSeq, MemberInitDTO memberInitDTO) throws Exception {
        // get data using ID
        Optional<Member> selectedMember = memberRepository.findById(memberSeq);
        Member updatedMember;

        if(selectedMember.isPresent()){
            Member member = selectedMember.get();

            member.setNickname(memberInitDTO.getNickname());
            member.setMemberProfile(memberInitDTO.getMemberProfile());
            member.setMemberState(memberInitDTO.getMemberState());
            member.setModifyTime(new Date());

            updatedMember = memberRepository.save(member);
        }

        else
            throw new Exception();

        return updatedMember;
    }

    @Override
    public void DeleteMemberById(long memberSeq) throws Exception {
        Optional<Member> selectedMember = memberRepository.findById(memberSeq);

        if (selectedMember.isPresent()){
            Member member = selectedMember.get();
            memberRepository.delete(member);
        }

        else
            throw new Exception();
    }

    @Override
    public boolean findByNickname(String nickname) {
        Optional<Boolean> selectedMember = memberRepository.findByNickname(nickname);

        return selectedMember.isPresent();
    }

    @Override
    public List<Member> InsertMembers(List<Member> members) {
        List<Member> savedMembers = memberRepository.saveAll(members);

        return savedMembers;
    }

    @Override
    public boolean findMemberByEmailReturnBool(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Override
    public Member SelectMemberByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

}
