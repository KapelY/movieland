package com.study.repository;

import com.study.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> findAll();
}
