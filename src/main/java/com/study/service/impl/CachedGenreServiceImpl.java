package com.study.service.impl;

import com.study.entity.Genre;
import com.study.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Primary
public class CachedGenreServiceImpl implements GenreService {
    private final GenreServiceImpl genreService;
    private List<Genre> cache;

    public CachedGenreServiceImpl(GenreServiceImpl genreService) {
        this.genreService = genreService;
        cache = genreService.findAll();
    }

    @Override
    public List<Genre> findAll() {
        return cache;
    }

    @Scheduled(cron = "* * 0/4 * * *")
    public void scheduleTaskUsingCronExpression() {
        cache = genreService.findAll();
        log.info("scheduled refresh genres using cron jobs at " + LocalDateTime.now());
    }
}
