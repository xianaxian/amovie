package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.from.RegisterUserFrom;
import com.ecjtu.amovie.entity.User;
import com.ecjtu.amovie.service.UserService;
import com.ecjtu.amovie.utils.MD5Utils;
import com.ecjtu.amovie.utils.result.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author xianaixan
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ResponseBody
    @RequestMapping("/register")
    public JsonResult<String> register(RegisterUserFrom rUser) {
        User user=new User();
        BeanUtils.copyProperties(rUser, user);
        int i = userService.register(user);
        if (i==1){
            return JsonResult.success("注册成功","/");
        }else {
            return JsonResult.error(400,"注册失败");
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public JsonResult<String> login(String email, String password, HttpSession session) {
        User login = userService.findOneByEmail(email);
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


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
