package com.ecjtu.amovie.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author xianaixan
 */
@Controller
@RequestMapping
public class RouterController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }




//    @GetMapping("/book1.html")
//    public String book1(){
//        return "book1";
//    }
//    @GetMapping("/book2.html")
//    public String book2(){
//        return "book2";
//    }
//    @GetMapping("/book-final.html")
//    public String book3_buy(){
//        return "book-final";
//    }

//
//    @GetMapping("/ticket.html")
//    public String ticket(){
//        return "ticket";
//    }
//
//    @GetMapping("/register.html")
//    public String register(){
//        return "register";
//    }
}
