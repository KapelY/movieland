package com.study.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    private long id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;
}
