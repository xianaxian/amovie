package com.ecjtu.amovie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.HashSet;

/**
 * @author xianaxian  2019-09-02 16:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFrom {
    private HashSet<String> bookedSeated;
    private Integer sceneId;
    private Integer ticketNum;
    private Float totalPrice;
    private Float price;
}
