package com.study.controller;

import com.study.entity.Genre;
import com.study.repository.GenreDao;
import com.study.repository.impl.DefaultGenreDao;
import com.study.service.GenreService;
import com.study.service.impl.DefaultGenreService;
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
class GenreControllerITest {
    List<Genre> list = List.of(
            new Genre(1L, "драма"),
            new Genre(2L, "криминал"),
            new Genre(3L, "фэнтези"),
            new Genre(4L, "детектив")
    );

    @Autowired
    private GenreController genreController;

    @Autowired
    private GenreService genreService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

    @SneakyThrows
    @Test
    void findAll() {
        when(genreService.findAll()).thenReturn(list);
        mockMvc.perform(get("/genre/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].name", Matchers.is("драма")))
                .andExpect(jsonPath("$.[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$.[1].name", Matchers.is("криминал")))
                .andExpect(jsonPath("$.[2].id", Matchers.is(3)))
                .andExpect(jsonPath("$.[2].name", Matchers.is("фэнтези")))
                .andExpect(jsonPath("$.[3].id", Matchers.is(4)))
                .andExpect(jsonPath("$.[3].name", Matchers.is("детектив")));
    }

    @Configuration
    public static class TestConfig {
        @Bean
        public JdbcTemplate jdbcTemplate() {
            return mock(JdbcTemplate.class);
        }

        @Bean
        public GenreDao genreDao() {
            return new DefaultGenreDao(jdbcTemplate());
        }

        @Bean
        public GenreService genreService() {
            return new DefaultGenreService(genreDao());
        }

        @Bean
        public GenreController genreController() {
            return new GenreController(genreService());
        }
    }
}