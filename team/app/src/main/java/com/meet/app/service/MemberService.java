package com.meet.app.service;

import com.meet.app.dto.MemberDTO;
import com.meet.app.entity.Member;
import com.meet.app.entity.School;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface MemberService {

    void register(MemberDTO memberDTO);

    Member login(Map<String, String> member);

    List<MemberDTO> getList();

    MemberDTO getMember(String id);

    void modifyMember(MemberDTO memberDTO);

    void deleteMember(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO){

        School school = School.builder().id(memberDTO.getSchoolID()).build();

        Member member = Member.builder()
                .id(memberDTO.getMemberID())
                .nickname(memberDTO.getNickname())
                .department(memberDTO.getDepartment())
                .stuNum(memberDTO.getStuNum())
                .password(memberDTO.getPassword())
                .name(memberDTO.getName())
                .isMen(memberDTO.isMen())
                .birth(memberDTO.getBirth())
                .school(school)
                .build();

        return member;
    }

    default MemberDTO entityToDTO(Member member){

        MemberDTO memberDTO = MemberDTO.builder()
                .memberID(member.getId())
                .nickname(member.getNickname())
                .department(member.getDepartment())
                .stuNum(member.getStuNum())
                .password(member.getPassword())
                .name(member.getName())
                .level((int) member.getLevel())
                .isMen(member.isMen())
                .birth(member.getBirth())
                .isDel(member.isDel())
                .schoolID(member.getSchool().getId())
                .build();

        return memberDTO;
    }
}
