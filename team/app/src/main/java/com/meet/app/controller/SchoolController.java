package com.meet.app.controller;

import com.meet.app.dto.SchoolDTO;
import com.meet.app.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody SchoolDTO schoolDTO){

        log.info("schoolDTO : " + schoolDTO);

        schoolService.register(schoolDTO);

        return HttpStatus.OK;
    }
}
