package com.meet.app.controller;

import com.meet.app.dto.CategoryDTO;
import com.meet.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody CategoryDTO categoryDTO){

        log.info("categoryDTO : " + categoryDTO);

        categoryService.categoryRegister(categoryDTO);

        return HttpStatus.OK;
    }
}
