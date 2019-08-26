package com.ecjtu.amovie.from;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author xianaxian  2019-08-23 14:31
 * 用户注册时的数据
 */
@Data
public class RegisterUserFrom {

    @Email(message = "请输入正确的邮箱格式")
    @NotBlank(message = "邮箱不能为空")
    private String email;


    private String nickname;

    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 重复的密码，确认两次密码相同
     */
    private String rpassword;

    @NotBlank(message = "电话号码不为空")
    private String phone;

    private Integer gender;

    private Integer role=1;
}
