package com.meet.app.service;

import com.meet.app.dto.SchoolDTO;

import java.util.List;

public interface SchoolService {

    void register(SchoolDTO schoolDTO);

    SchoolDTO read(Integer id);

    List<SchoolDTO> getList();
}
