package com.meet.app.service;

import com.meet.app.dto.PrisonDTO;
import com.meet.app.entity.Member;
import com.meet.app.entity.Prison;
import com.meet.app.repository.MemberRepository;
import com.meet.app.repository.PrisonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrisonServiceImpl implements PrisonService{

    private final PrisonRepository prisonRepository;
    private final MemberRepository memberRepository;

    @Override
    public void register(PrisonDTO prisonDTO, String memberID) {

        log.info("prisonDTO : " + prisonDTO);

<<<<<<< HEAD
        Optional<Member> member2 = memberRepository.findById(prisonDTO.getBadMemberID());
        Optional<Member> member1 = memberRepository.findById(memberID);
=======
        Optional<Member> member1 = memberRepository.findById(memberID);
        Optional<Member> member2 = memberRepository.findById(prisonDTO.getBadMemberID());
>>>>>>> master

        Member member = member1.get();
        Member badMember = member2.get();

        Prison prison = dtoToEntity(prisonDTO, member, badMember);
        prisonRepository.save(prison);
    }

    @Override
    public PrisonDTO get(Long id) {

        log.info("id : " + id);

        Optional<Prison> result = prisonRepository.findById(id);
        Prison prison = result.get();

        PrisonDTO prisonDTO = entityToDTO(prison);

        return prisonDTO;
    }

    @Override
    public List<PrisonDTO> getList() {
        List<Prison> prisons = prisonRepository.findAll();

        List<PrisonDTO> prisonDTOS = new ArrayList<>();
        prisons.forEach(entity -> {
            PrisonDTO prisonDTO = entityToDTO(entity);
            prisonDTOS.add(prisonDTO);
        });

        return prisonDTOS;
    }

    @Override
    public void modify(PrisonDTO prisonDTO, String memberID) {
        Optional<Prison> result = prisonRepository.findById(prisonDTO.getId());
        Optional<Member> member1 = memberRepository.findById(memberID);
        Optional<Member> member2 = memberRepository.findById(prisonDTO.getBadMemberID());

        Prison entity = result.get();
        Member member = member1.get();
        Member badMember = member2.get();

        Prison prison = Prison.builder()
                .id(entity.getId())
                .title(prisonDTO.getTitle())
                .content(prisonDTO.getContent())
                .member(member)
                .bad(badMember)
                .build();

        prisonRepository.save(prison);
    }

    @Override
    public void delete(Long id) {

        log.info("id : " + id);

        prisonRepository.deleteById(id);
    }
}
