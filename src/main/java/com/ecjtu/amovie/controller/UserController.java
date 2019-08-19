package com.ecjtu.amovie.controller;

import com.ecjtu.amovie.entity.User;
import com.ecjtu.amovie.service.UserServce;
import com.ecjtu.amovie.utils.MD5Utils;
import com.ecjtu.amovie.utils.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServce userServce;

    @RequestMapping("/isEmailExit")
    public void isEmailExit(@RequestParam("email") String email) {
        boolean emailExit = userServce.isEmailExit(email);
        System.out.println();
    }

    @RequestMapping("/register")
    public void register(@RequestParam("user") User user) {
        if (userServce.isEmailExit(user.getEmail())) {
            return;
        }
        String salt = MD5Utils.getSalt();
        user.setSalt(salt);
        user.setPassword(MD5Utils.md5(user.getPassword(), salt));
        int i = userServce.saveOne(user);
    }

    @ResponseBody
    @PostMapping("/login")
    public JsonResult<String> login(String email, String password, HttpSession session) {
        User login = userServce.findOneByEmail(email);
        if (login == null) {
            return JsonResult.error(233, "登陆失败，错误的用户名或密码");
        }
        if (login.getPassword().equals(MD5Utils.md5(password, login.getSalt()))) {
            //登陆成功
            session.setAttribute("user", login);
            return JsonResult.success("登陆成功", null);
        } else {
            System.out.println("Hello World");
            return JsonResult.error(233, "登陆失败，错误的用户名或密码");
            //登陆失败
        }
    }


//    @ResponseBody
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
