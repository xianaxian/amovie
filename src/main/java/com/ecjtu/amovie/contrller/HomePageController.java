package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.service.MovieService;
import com.ecjtu.amovie.form.MovieResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author xianaxian  2019-08-30 17:00
 */

@Controller
public class HomePageController {
    private final MovieService movieService;

    public HomePageController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        List<MovieResult> bestMovie = movieService.bestMovie();
        List<MovieResult> released = movieService.released();
        Set ranNum=new HashSet();
        while (ranNum.size()*3 <released.size()){
            int j = new Random().nextInt(released.size() + 1);
            if (!ranNum.contains(j)){
                ranNum.add(j);
            }
        }
        mav.addObject("released", released);
        mav.addObject("bestMovie", bestMovie);
        mav.addObject("ran", ranNum);
        mav.setViewName("index");
        return mav;
    }
}
