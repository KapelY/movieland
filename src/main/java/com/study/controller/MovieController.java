package com.study.controller;


import com.study.entity.Movie;
import com.study.entity.sorting.Fields;
import com.study.entity.sorting.Order;
import com.study.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.study.entity.sorting.Fields.*;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> randomMovies() {
        return movieService.getRandom();
    }

    @GetMapping(value = "/genre/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> moviesByGenre(@PathVariable int id) {
        return movieService.getMoviesByGenre(id);
    }
}
