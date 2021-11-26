package com.meet.app.service;

import com.meet.app.dto.SchoolDTO;
import com.meet.app.entity.School;
import com.meet.app.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository schoolRepository;

    // 학교 생성
    @Override
    public void register(SchoolDTO schoolDTO) {

        log.info("schoolDTO : " + schoolDTO);

        School school = School.builder()
                .name(schoolDTO.getName())
                .build();

        schoolRepository.save(school);
    }

    // 학교 불러오기
    @Override
    public SchoolDTO read(Integer id) {

        log.info("id : " + id);

        School school = schoolRepository.findById(id).get();

        SchoolDTO dto = SchoolDTO.builder()
                .id(school.getId())
                .name(school.getName())
                .build();

        return dto;
    }

    // 학교 리스트
    @Override
    public List<SchoolDTO> getList() {

        List<School> results = schoolRepository.findAll();

        List<SchoolDTO> dtoList = new ArrayList<>();

        results.forEach(result -> {

            SchoolDTO dto = SchoolDTO.builder().id(result.getId()).name(result.getName()).build();

            dtoList.add(dto);
        });

        return dtoList;
    }
}
