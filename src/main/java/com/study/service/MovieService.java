package com.study.service;

import com.study.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> findAll();
    List<Movie> getRandom();
}
