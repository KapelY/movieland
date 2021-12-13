package com.study.repository;

import com.study.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
}
