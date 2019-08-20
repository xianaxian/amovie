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
public class Scene {
    private Integer id;
    private Integer movieId;
    private String movieName;
    private Float price;
    /**
     * 每场固定166个座位
     */
    private Integer seatNum=166;
    private Date showTime;
    private String bookedSeat;

}
