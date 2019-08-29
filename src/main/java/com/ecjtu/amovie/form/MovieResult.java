package com.ecjtu.amovie.form;

import com.ecjtu.amovie.api.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author xianaxian  2019-08-29 10:01
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResult {
    private Integer id;
    private String name;
    /**
     * 电影时长
     */
    private String duration;
    private String directors;
    private String actors;
    private Date releaseDate;
    private Integer status;
    /**
     * 电影剧情
     */
    private String plot;
    private String poster;
    private String country;
//    /**
//     * 冗余的字段
//     */
//    private Integer[] categoryIds;

    private List<Category> categories;
    private Integer i;
    private Double score=0.0;
    /**
     * 给该电影评分的人
     */
    private Integer num=0;
}