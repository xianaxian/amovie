package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.service.MovieService;
import com.ecjtu.amovie.form.MovieResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author xianaxian  2019-08-30 17:00
 */

@Controller
public class HomePageController {
    @Autowired
    private MovieService movieService;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        List<MovieResult> bestMovie = movieService.bestMovie();
        mav.addObject("bestMovie", bestMovie);
        mav.setViewName("index");
        return mav;
    }
}
