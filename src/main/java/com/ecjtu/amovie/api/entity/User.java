package com.ecjtu.amovie.api.entity;

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
public class User {
    private Integer id;
    private String nickname;
    private String email;
    private String password;
    private String salt;
    private String phone;
    private Integer gender;
    private Integer role;
}
