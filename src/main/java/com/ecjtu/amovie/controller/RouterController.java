package com.ecjtu.amovie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author xianaixan
 */
@Controller
@RequestMapping
public class RouterController {
    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
    @GetMapping("/news.html")
    public String news(){
        return "news";
    }

    @GetMapping(value = {"/rate","rate.html"})
    public String rate(){
        return "rate";
    }

    @GetMapping(value = {"/news-list","/news-list.html"})
    public String newList(){
        return "news-list";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping(value = {"/movie-list","/movie-list.html"})
    public String movieList(){
        return "movie-list";
    }
    @GetMapping("/movie.html")
    public String movie(){
        return "movie";
    }

    @GetMapping("/book1.html")
    public String book1(){
        return "book1";
    }
    @GetMapping("/book2.html")
    public String book2(){
        return "book2";
    }
    @GetMapping("/book3-buy.html")
    public String book3_buy(){
        return "book3-buy";
    }

    @GetMapping("/watchlist.html")
    public String watchlist(){
        return "watchlist";
    }

    @GetMapping("/ticket.html")
    public String ticket(){
        return "ticket";
    }

    @GetMapping("/register.html")
    public String register(){
        return "register";
    }
}
