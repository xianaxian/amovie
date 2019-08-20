package com.ecjtu.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Integer id;
    private String content;
    private Integer movieId;
    private  Integer userId;
    private Date createTime;

}
