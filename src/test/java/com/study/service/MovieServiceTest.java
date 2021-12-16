package com.study.service;

import com.study.entity.Movie;
import com.study.repository.MovieDao;
import com.study.repository.impl.MovieDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.study.repository.impl.MovieDaoImpl.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {
    private final JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    private final MovieDao movieDao = new MovieDaoImpl(jdbcTemplate);

    List<Movie> list = List.of(
            Movie.builder().id(1L).nameRussian("Укрощение строптивого").nameNative("Il bisbetico domato").build(),
            Movie.builder().id(2L).nameRussian("Гладиатор").nameNative("Gladiator").build(),
            Movie.builder().id(3L).nameRussian("Бойцовский клуб").nameNative("Fight Club").build(),
            Movie.builder().id(4L).nameRussian("Форрест Гамп").nameNative("Forrest Gump").build()
    );
    List<Movie> listOfThree = List.of(
            Movie.builder().id(1L).nameRussian("Укрощение строптивого").nameNative("Il bisbetico domato").build(),
            Movie.builder().id(2L).nameRussian("Гладиатор").nameNative("Gladiator").build(),
            Movie.builder().id(3L).nameRussian("Бойцовский клуб").nameNative("Fight Club").build()
    );

    @Test
    void findAll() {
        when(jdbcTemplate.query(GET_ALL_MOVIES, MOVIE_ROW_MAPPER)).thenReturn(list);
        List<Movie> actual = movieDao.findAll();
        assertEquals(4, actual.size());
        assertEquals("Гладиатор", actual.get(1).getNameRussian());
        assertEquals("Forrest Gump", actual.get(3).getNameNative());
    }

    @Test
    void getRandom() {
        when(jdbcTemplate.query(GET_RANDOM_MOVIES, MOVIE_ROW_MAPPER)).thenReturn(listOfThree);
        List<Movie> actual = movieDao.getRandom();
        assertEquals(3, actual.size());
    }
}