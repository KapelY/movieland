package com.study.repository;

import com.study.entity.Movie;
import com.study.entity.sorting.Fields;
import com.study.entity.sorting.Order;

import java.util.List;
import java.util.Map;

public interface MovieDao {
    List<Movie> findAll(Map<Fields, Order> map);
    List<Movie> getRandom();
    List<Movie> getMoviesByGenre(int id);
}
