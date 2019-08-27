package com.ecjtu.amovie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @author xianaixan
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scene {
    private Integer id;
    private Integer movieId;
    private String movieName;
    private Float price;
    /**
     * 每场固定166个座位
     */
    private final Integer seatNum = 166;
    private String showtime;
    private String bookedSeat;

}
