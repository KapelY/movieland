package com.study.controller;


import com.study.entity.Genre;
import com.study.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Genre> findAll() {
        return genreService.findAll();
    }

}
