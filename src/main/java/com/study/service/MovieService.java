package com.study.service;

import com.study.entity.Movie;
import com.study.entity.sorting.Fields;
import com.study.entity.sorting.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MovieService {
    List<Movie> findAll(Map<Fields, Order> map);
    List<Movie> getRandom();
    List<Movie> getMoviesByGenre(int id);
}
