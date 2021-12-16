package com.study.service.impl;

import com.study.entity.Genre;
import com.study.repository.GenreDao;
import com.study.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}
