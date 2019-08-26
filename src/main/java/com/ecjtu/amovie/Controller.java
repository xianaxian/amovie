package com.ecjtu.amovie;


import com.ecjtu.amovie.entity.Movie;
import com.ecjtu.amovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author xianaixan
 */
@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {


    private final MovieRepository movieRepository;

    public Controller(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping("/all")
    public ModelAndView findAll() {
//        DispatcherServlet
        List<Movie> movies = movieRepository.selectAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("movies", movies);
        mav.setViewName("movie");
        return mav;
    }


}
