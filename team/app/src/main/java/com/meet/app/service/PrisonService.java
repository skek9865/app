package com.meet.app.service;

import com.meet.app.dto.PrisonDTO;
import com.meet.app.entity.Member;
import com.meet.app.entity.Prison;

import java.util.List;

public interface PrisonService {

    void register(PrisonDTO prisonDTO, String memberID);

    PrisonDTO getOne(Long id);

    List<PrisonDTO> getList();

    void modify(PrisonDTO prisonDTO, String memberID);

    void remove(Long id);

    default Prison dtoToEntity(PrisonDTO prisonDTO, Member member, Member badMember){

        Prison prison = Prison.builder()
                .content(prisonDTO.getContent())
                .title(prisonDTO.getTitle())
                .member(member)
                .bad(badMember)
                .build();

        return prison;
    }

    default PrisonDTO entityToDTO(Prison prison){

        PrisonDTO prisonDTO = PrisonDTO.builder()
                .prisonID(prison.getId())
                .title(prison.getTitle())
                .content(prison.getContent())
                .memberID(prison.getMember().getId())
                .badMemberID(prison.getBad().getId())
                .build();

        return prisonDTO;
    }
}
