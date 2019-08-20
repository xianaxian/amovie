package com.ecjtu.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 电影
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Integer id;
    private String name;
    //电影时长
    private String duration;
    private String directors;
    private String actors;
    private Date releaseDate;
    private String categoryId;
    private Integer status;
    //电影剧情
    private String plot;
    private String poster;
    private String country;

    private List<Category> list;

}
