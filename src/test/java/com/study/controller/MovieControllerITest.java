package com.study.controller;

import com.study.entity.Movie;
import com.study.repository.MovieDao;
import com.study.repository.MovieDaoImpl;
import com.study.service.MovieService;
import com.study.service.MovieServiceImpl;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration
class MovieControllerITest {
    List<Movie> list = List.of(
            Movie.builder().id(1L).nameRussian("Укрощение строптивого").nameNative("Il bisbetico domato").build(),
            Movie.builder().id(2L).nameRussian("Гладиатор").nameNative("Gladiator").build(),
            Movie.builder().id(3L).nameRussian("Бойцовский клуб").nameNative("Fight Club").build(),
            Movie.builder().id(4L).nameRussian("Форрест Гамп").nameNative("Forrest Gump").build()
    );
    List<Movie> listOfThree = List.of(
            Movie.builder().id(1L).nameRussian("Форрест Гамп").nameNative("Forrest Gump").build(),
            Movie.builder().id(2L).nameRussian("Гладиатор").nameNative("Gladiator").build(),
            Movie.builder().id(3L).nameRussian("Бойцовский клуб").nameNative("Fight Club").build()
    );

    @Autowired
    private MovieController movieController;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @SneakyThrows
    @Test
    void findAll() {
        when(movieService.findAll()).thenReturn(list);
        mockMvc.perform(get("/movie/").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].nameNative", Matchers.is("Il bisbetico domato")))
                .andExpect(jsonPath("$.[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$.[1].nameNative", Matchers.is("Gladiator")))
                .andExpect(jsonPath("$.[2].id", Matchers.is(3)))
                .andExpect(jsonPath("$.[2].nameNative", Matchers.is("Fight Club")));
    }

    @SneakyThrows
    @Test
    void randomMovies() {
        when(movieService.getRandom()).thenReturn(listOfThree);
        mockMvc.perform(get("/movie/random").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].nameNative", Matchers.is("Forrest Gump")))
                .andExpect(jsonPath("$.[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$.[1].nameNative", Matchers.is("Gladiator")))
                .andExpect(jsonPath("$.[2].id", Matchers.is(3)))
                .andExpect(jsonPath("$.[2].nameNative", Matchers.is("Fight Club")));
    }

    @SneakyThrows
    @Test
    void moviesByGenre() {
        when(movieService.getMoviesByGenre(1)).thenReturn(listOfThree);
        mockMvc.perform(get("/movie/genre/1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].nameNative", Matchers.is("Forrest Gump")))
                .andExpect(jsonPath("$.[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$.[1].nameNative", Matchers.is("Gladiator")))
                .andExpect(jsonPath("$.[2].id", Matchers.is(3)))
                .andExpect(jsonPath("$.[2].nameNative", Matchers.is("Fight Club")));
    }


    @Configuration
    public static class TestConfig {
        @Bean
        public JdbcTemplate jdbcTemplate() {
            return mock(JdbcTemplate.class);
        }

        @Bean
        public MovieDao movieDao() {
            return new MovieDaoImpl(jdbcTemplate());
        }

        @Bean
        public MovieService movieService() {
            return new MovieServiceImpl(movieDao());
        }

        @Bean
        public MovieController movieController() {
            return new MovieController(movieService());
        }
    }
}