package com.study.controller;


import com.study.entity.Movie;
import com.study.entity.sorting.Fields;
import com.study.entity.sorting.Order;
import com.study.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.study.entity.sorting.Fields.PRICE;
import static com.study.entity.sorting.Fields.RATING;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<Movie> findAll(@RequestParam(required = false) String rating,
                               @RequestParam(required = false) String price) {
        Map<Fields, Order> map = new HashMap<>();
        if (rating != null) {
            map.put(RATING, Order.valueOf(rating));
        }
        if (price != null) {
            map.put(PRICE, Order.valueOf(price));
        }
        return movieService.findAll(map);
    }

    @GetMapping(value = "/random")
    public List<Movie> randomMovies() {
        return movieService.getRandom();
    }

    @GetMapping(value = "/genre/{id}")
    public List<Movie> moviesByGenre(@PathVariable int id) {
        return movieService.getMoviesByGenre(id);
    }
}
