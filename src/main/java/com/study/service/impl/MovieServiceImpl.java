package com.study.service.impl;

import com.study.entity.Movie;
import com.study.repository.MovieDao;
import com.study.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom();
    }

    @Override
    public List<Movie> getMoviesByGenre(int id) {
        return movieDao.getMoviesByGenre(id);
    }
}
