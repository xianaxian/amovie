package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.Movie;
import com.ecjtu.amovie.api.entity.Review;
import com.ecjtu.amovie.api.entity.Scene;
import com.ecjtu.amovie.api.entity.User;
import com.ecjtu.amovie.api.service.MovieService;
import com.ecjtu.amovie.api.service.ReviewService;
import com.ecjtu.amovie.api.service.SceneService;
import com.ecjtu.amovie.api.service.WatchListService;
import com.ecjtu.amovie.form.MovieResult;
import com.ecjtu.amovie.form.RateListForm;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collections;
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
    private final WatchListService watchListService;

    public MovieController(MovieService movieService, SceneService sceneService, ReviewService reviewService, WatchListService watchListService) {
        this.movieService = movieService;
        this.sceneService = sceneService;
        this.reviewService = reviewService;
        this.watchListService = watchListService;
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
                                @RequestParam(name = "category", required = false,defaultValue = "0")int categoryId,
                                HttpSession session) {
        PageInfo<MovieResult> movies;
        if (categoryId==0){
            movies=movieService.moviePageNoLogin(pageNum, pageSize);
        }else {
            movies=movieService.moviePageNoLoginCatetgory(pageNum, pageSize, categoryId);
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("movies",movies);
        mav.addObject("category", categoryId);
        mav.setViewName("movie-list");
        User user = (User) session.getAttribute("user");
        List<Integer> watchListId = Collections.emptyList();
        if (user!=null){
            //WatchList ID 该用户添加到观看列表的电影的id
             watchListId = watchListService.selectByUser(user.getId());
        }
        mav.addObject("WLID", watchListId);
        return mav;
    }

    @GetMapping("/search")
    @ResponseBody
    public ModelAndView search(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                               @RequestParam(name = "type", required = false,defaultValue = "-1")int searchType,
                               @RequestParam(name = "value", required = false,defaultValue = "")String value,
                               HttpSession session){
        PageInfo<MovieResult> movies;
        movies=movieService.search(pageNum, pageSize, searchType, value);

        ModelAndView mav=new ModelAndView();
        mav.addObject("movies",movies);
        mav.addObject("type", searchType);
        mav.addObject("value", value);
        mav.setViewName("movie-list");
        User user = (User) session.getAttribute("user");
        List<Integer> watchListId= Collections.emptyList();
        if (user!=null){
            /**
             * WatchList ID 该用户添加到观看列表的电影的id
             */
            watchListId = watchListService.selectByUser(user.getId());
        }
        mav.addObject("WLID", watchListId);
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

    @GetMapping("/rate")
    public ModelAndView getRankingList(){
        ModelAndView mav= new ModelAndView();
        List<RateListForm> rankingList = movieService.getRankingList();
        mav.addObject("list", rankingList);
        mav.setViewName("rate");
        return mav;
    }
}
