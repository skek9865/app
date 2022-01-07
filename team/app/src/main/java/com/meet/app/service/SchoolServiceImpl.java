package com.meet.app.service;

import com.meet.app.dto.SchoolDTO;
import com.meet.app.entity.School;
import com.meet.app.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository schoolRepository;

    // 학교 생성
    @Override
    public void register(SchoolDTO schoolDTO) {
        log.info("SchoolService - register");
        log.info("schoolDTO : " + schoolDTO);

        School school = School.builder()
                .name(schoolDTO.getName())
                .build();

        schoolRepository.save(school);
    }

    // 학교 불러오기
    @Override
    public SchoolDTO getOne(Integer id) {
        log.info("SchoolService - getOne");
        log.info("id : " + id);

        School school = schoolRepository.findById(id).get();

        SchoolDTO dto = SchoolDTO.builder()
                .schoolID(school.getId())
                .name(school.getName())
                .build();

        return dto;
    }

    // 학교 리스트
    @Override
    public List<SchoolDTO> getList() {
        log.info("SchoolService - getList");

        List<School> results = schoolRepository.findAll();

        List<SchoolDTO> dtoList = new ArrayList<>();

        results.forEach(result -> {

            SchoolDTO dto = SchoolDTO.builder().schoolID(result.getId()).name(result.getName()).build();

            dtoList.add(dto);
        });

        return dtoList;
    }
}
