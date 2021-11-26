package com.meet.app.service;

import com.meet.app.dto.MemberDTO;
import com.meet.app.entity.Member;
import com.meet.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 회원 등록
    @Override
    public void register(MemberDTO memberDTO) {

        log.info("memberDTO : " + memberDTO);

        Member member = dtoToEntity(memberDTO);

        memberRepository.save(member);
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

        Member member = memberRepository.findById(id).get();

        MemberDTO memberDTO = entityToDTO(member);

        return memberDTO;
    }
}
