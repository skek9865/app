package com.meet.app.service;

import com.meet.app.dto.MemberDTO;
import com.meet.app.entity.Member;
import com.meet.app.entity.School;
import com.meet.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 등록
    @Override
    public void register(MemberDTO memberDTO) {

        log.info("memberDTO : " + memberDTO);

        String encodePassword = passwordEncoder.encode(memberDTO.getPassword());

        School school = School.builder().id(memberDTO.getSchoolID()).build();

        Member member = Member.builder()
                .id(memberDTO.getMemberID())
                .nickname(memberDTO.getNickname())
                .department(memberDTO.getDepartment())
                .stuNum(memberDTO.getStuNum())
                .password(encodePassword)
                .name(memberDTO.getName())
                .isMen(memberDTO.isMen())
                .birth(memberDTO.getBirth())
                .school(school)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        memberRepository.save(member);
    }

    // 회원 로그인
    @Override
    public Member login(Map<String, String> member) {
        Member findMember = memberRepository.findById(member.get("id"))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 아이디입니다"));

        if (!passwordEncoder.matches(member.get("password"), findMember.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다");
        }
        return findMember;
    }

    // 회원 리스트
    @Override
    public List<MemberDTO> getList() {

        List<Member> all = memberRepository.findAll();

        List<MemberDTO> dtoList = new ArrayList<>();

        all.forEach(entity -> {

            MemberDTO dto = entityToDTO(entity);

            dtoList.add(dto);
        });

        return dtoList;
    }

    // 회원 조회
    @Override
    public MemberDTO getMember(String id) {

//        Member member = memberRepository.findById(id).get();
        Member member = memberRepository.findById(id).get();

        MemberDTO memberDTO = entityToDTO(member);

        return memberDTO;
    }

    // 회원 정보 수정
    @Override
    public void modifyMember(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getMemberID()).get();

        member.modifyUser(memberDTO.getNickname(), passwordEncoder.encode(memberDTO.getPassword()));
    }

    //회원 삭제
    @Override
    public void deleteMember(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getMemberID()).get();

        member.deleteUser();
        log.info("delete member"+ member.getId());
    }
}
