package com.ecjtu.amovie.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xianaxian  2019-09-02 20:43
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResult {
        private Integer id;
        private Integer status;
        private Integer userId;
        private Date createTime;
        private Integer sceneId;
        private Integer ticketNum;
        private Float totalPrice;
        private String bookedSeated;
        private String movieName;
        private String showtime;
}
