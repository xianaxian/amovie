package com.ecjtu.amovie.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author xianaixan
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Integer id;
    private String content;
    private String title;
    private Date createTime;
}
