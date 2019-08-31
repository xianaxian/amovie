package com.ecjtu.amovie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xianaxian  2019-08-29 21:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateListForm {
    private Integer no;
    private Integer id;
    private String name;
    private Double score=0.0;
    private Integer num=0;
}
