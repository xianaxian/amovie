package com.ecjtu.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author xianaixan
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;


}
