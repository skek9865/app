package com.meet.app.controller;

import com.meet.app.config.security.JwtTokenProvider;
import com.meet.app.dto.PrisonDTO;
import com.meet.app.service.PrisonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/prison")
public class PrisonController {

    private final PrisonService prisonService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public HttpStatus register(PrisonDTO prisonDTO, @RequestHeader("JWT-TOKEN") String token){
        log.info("PrisonController - register");
        log.info("prisonDTO : " + prisonDTO);
        log.info("token : " + token);

        String memberID = jwtTokenProvider.getMemberPk(token);

        prisonService.register(prisonDTO, memberID);

        return HttpStatus.OK;
    }

    @PutMapping("/modify")
    public HttpStatus modify(PrisonDTO prisonDTO, @RequestHeader("JWT-TOKEN") String token){
        log.info("PrisonController - modify");
        log.info("prisonDTO : " + prisonDTO);
        log.info("token : " + token);

        String memberID = jwtTokenProvider.getMemberPk(token);

        prisonService.modify(prisonDTO, memberID);

        return HttpStatus.OK;
    }
}
