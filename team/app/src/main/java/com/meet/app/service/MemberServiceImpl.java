package com.meet.app.service;

import com.meet.app.dto.MemberDTO;
import com.meet.app.entity.Member;
import com.meet.app.entity.School;
import com.meet.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 등록
    @Override
    public void register(MemberDTO memberDTO) {
        log.info("MemberService - register");
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
        log.info("MemberService - login");
        log.info("member : " + member);

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
        log.info("MemberService - getList");

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
    public MemberDTO getOne(String id) {
        log.info("MemberService - getOne");
        log.info("id : " + id);

        Member member = memberRepository.findById(id).get();

        MemberDTO memberDTO = entityToDTO(member);

        return memberDTO;
    }

    // 회원 정보 수정
    @Override
    public void modify(MemberDTO memberDTO) {
        log.info("MemberService - modify");
        log.info("memberDTO : " + memberDTO);

        Member member = memberRepository.findById(memberDTO.getMemberID()).get();

        member.modifyUser(memberDTO.getNickname(), passwordEncoder.encode(memberDTO.getPassword()));
    }

    //회원 삭제
    @Override
    public void remove(MemberDTO memberDTO) {
        log.info("MemberService - getList");
        log.info("memberDTO : " + memberDTO);

        Member member = memberRepository.findById(memberDTO.getMemberID()).get();

        member.deleteUser();
        log.info("delete member"+ member.getId());
    }
}
