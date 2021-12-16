package com.study.repository.impl;

import com.study.entity.Movie;
import com.study.entity.sorting.Fields;
import com.study.entity.sorting.Order;
import com.study.repository.MovieDao;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Repository
@AllArgsConstructor
public class MovieDaoImpl implements MovieDao {
    public static final String GET_ALL_MOVIES = "SELECT id, name_russian, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies ORDER BY ";
    public static final String GET_RANDOM_MOVIES = "SELECT id, name_russian, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies ORDER BY random() LIMIT 3;";
    public static final String GET_MOVIES_BY_GENRE = "SELECT id, name_russian, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies LEFT JOIN movie_genre as mg ON mg.movie_id = movies.id WHERE mg.genre_id=?;";
    public static final RowMapper<Movie> MOVIE_ROW_MAPPER = new MovieRowMapper();

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> findAll(Map<Fields, Order> map) {
        StringJoiner stringJoiner = new StringJoiner(", ", GET_ALL_MOVIES, ";");
        map.forEach((key, value) -> stringJoiner.add(key.getFieldName() + " " + value.getOrderValue()));
        return jdbcTemplate.query(stringJoiner.toString(), MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getRandom() {
        return jdbcTemplate.query(GET_RANDOM_MOVIES, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getMoviesByGenre(int id) {
        return jdbcTemplate.query(GET_MOVIES_BY_GENRE,
                new Object[]{id},
                new int[]{Types.INTEGER},
                MOVIE_ROW_MAPPER);
    }

    public static class MovieRowMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String nameRussian = rs.getString("name_russian");
            String nameNative = rs.getString("name_native");
            int yearOfRelease = rs.getInt("year_of_release");
            double rating = rs.getDouble("rating");
            int price = rs.getInt("price");
            String picturePath = rs.getString("picture_path");

            return Movie.builder()
                    .id(id)
                    .nameRussian(nameRussian)
                    .nameNative(nameNative)
                    .yearOfRelease(yearOfRelease)
                    .rating(rating)
                    .price(price)
                    .picturePath(picturePath).build();
        }
    }
}
