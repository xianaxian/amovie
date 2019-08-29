package com.ecjtu.amovie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author xianaxian  2019-08-29 16:38
 * 用户提交给电影评分的表单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateForm {
    @NotNull
    private Integer movieId;
    @NotNull
    private Integer score;
}
