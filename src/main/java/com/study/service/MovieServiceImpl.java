package com.study.service;

import com.study.entity.Movie;
import com.study.repository.MovieDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom();
    }


}
