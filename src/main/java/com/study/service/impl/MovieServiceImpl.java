package com.study.service.impl;

import com.study.entity.Movie;
import com.study.entity.sorting.Fields;
import com.study.entity.sorting.Order;
import com.study.repository.MovieDao;
import com.study.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Override
    public List<Movie> findAll(Map<Fields, Order> map) {
        return movieDao.findAll(map);
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
