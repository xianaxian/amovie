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
public class Order {
    private Integer id;
    private Integer status;
    private Integer userId;
    private Date createTime;
    private Integer sceneId;
    private Integer ticketNum;
    private Float totalPrice;
    private String bookedSeated;
}