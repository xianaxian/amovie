package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.News;
import com.ecjtu.amovie.api.service.MovieService;
import com.ecjtu.amovie.api.service.NewsService;
import com.ecjtu.amovie.form.MovieResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author xianaxian  2019-08-30 17:00
 */

@Controller
public class HomePageController {
    /**
     *  首页中部右边的图片显示占比
     */
    private final int size=3;
    private final MovieService movieService;
    private final NewsService newsService;

    public HomePageController(MovieService movieService, NewsService newsService) {
        this.movieService = movieService;
        this.newsService = newsService;
    }

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        List<MovieResult> bestMovie = movieService.bestMovie();
        List<MovieResult> released = movieService.released();
        Set ranNum=new HashSet();
        while (ranNum.size()*size <released.size()){
            int j = new Random().nextInt(released.size() + 1);
            if (!ranNum.contains(j)){
                ranNum.add(j);
            }
        }
        List<News> news = newsService.getLatestNews();
        mav.addObject("news", news);
        mav.addObject("released", released);
        mav.addObject("bestMovie", bestMovie);
        mav.addObject("ran", ranNum);
        mav.setViewName("index");
        return mav;
    }
}
