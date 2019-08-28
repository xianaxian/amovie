package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.Movie;
import com.ecjtu.amovie.api.entity.Review;
import com.ecjtu.amovie.api.entity.Scene;
import com.ecjtu.amovie.api.service.MovieService;
import com.ecjtu.amovie.api.service.ReviewService;
import com.ecjtu.amovie.api.service.SceneService;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author xianaixan
 */
@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final SceneService sceneService;
    private final ReviewService reviewService;

    public MovieController(MovieService movieService, SceneService sceneService, ReviewService reviewService) {
        this.movieService = movieService;
        this.sceneService = sceneService;
        this.reviewService = reviewService;
    }


    /**
     *
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param  categoryId 类别的id
     * @return ModelAndView
     */
    @GetMapping
    @ResponseBody
    public ModelAndView getList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                              @RequestParam(name = "category", required = false,defaultValue = "0")int categoryId) {
        PageInfo<Movie> movies;
        if (categoryId==0){
            movies=movieService.getMoviesByPage(pageNum, pageSize);
        }else {
            movies=movieService.getMoviesByPage(pageNum, pageSize, categoryId);
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("movies",movies);
        mav.addObject("category", categoryId);
        mav.setViewName("movie-list");
        return mav;
    }

    /**
     * 单个电影的页面
     * @param id 电影的id
     * @return ModelAndView
     */
    @GetMapping("/{id}")
    public ModelAndView getMovie(@PathVariable("id") Integer id) {
        Movie movie = movieService.getOneMovieById(id);
        List<Scene> scenes = sceneService.selectByMovieId(id);
        List<Review> reviews = reviewService.getMoviesReview(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("movie", movie);
        mav.addObject("scenes", scenes);
        mav.addObject("reviews",reviews);
        mav.setViewName("movie");
        return mav;
    }
    @PostMapping("/review")
    public JsonResult review(@RequestBody Review review){
        int i = reviewService.saveReview(review);
        if (i==1){
            return JsonResult.success("发表评论成功",null);
        }else {
            return JsonResult.error(400,"发表评论失败");
        }
    }
}
