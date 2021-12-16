package com.study.repository.impl;

import com.study.entity.Genre;
import com.study.repository.GenreDao;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class DefaultGenreDao implements GenreDao {
    private static final String GET_ALL_GENRES = "SELECT id, name FROM genres;";

    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(GET_ALL_GENRES, GENRE_ROW_MAPPER);
    }

    public static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Genre(id,name);
        }
    }
}
