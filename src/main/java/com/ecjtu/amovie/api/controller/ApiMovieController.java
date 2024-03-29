package com.ecjtu.amovie.api.controller;

import com.ecjtu.amovie.api.entity.Movie;
import com.ecjtu.amovie.api.service.MovieService;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xianaixan
 */
@CrossOrigin
@Controller
@RequestMapping("/api/movies")
public class ApiMovieController {
    private final MovieService movieService;


    public ApiMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * 获取电影列表
     *
     * @param pageNum 页码
     * @param pageSize 页每页大小
     * @return JSON数据
     */
    @GetMapping
    @ResponseBody
    public JsonResult news(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        PageInfo<Movie> movies = movieService.getMoviesByPage(pageNum, pageSize);
        return JsonResult.success("查询电影类别成功", movies);
    }


    /**
     * 获取电影信息
     *
     * @param id 电影的id
     * @return JSON数据
     */
    @GetMapping("/{id}")
    @ResponseBody
    public JsonResult movie(@PathVariable("id") Integer id) {
        Movie movie = movieService.getOneMovieById(id);
        JsonResult<Object> result;
        if (movie == null) {
            result = JsonResult.error(404, "没有找到电影");
        } else {
            result = JsonResult.success("查询该电影成功", movie);
        }
        return result;
    }

    /**
     * 创建电影信息
     *
     * @param movie 电影的信息
     * @return 返回的是JSON对象。statusCode----400---失败，200成功
     */
    @PostMapping()
    @ResponseBody
    public JsonResult movie(@RequestBody Movie movie) {
        int i = movieService.insertOneMovie(movie);

        JsonResult result;
        if (i == 1) {
            result = JsonResult.success("创建电影成功", null);
        } else {
            result = JsonResult.error(400, "创建失败");
        }
        return result;
    }


    /**
     * 更新电影
     *
     * @param id    电影的id
     * @param movie 电影的信息
     * @return 是否更新成功
     */
    @PutMapping("/{id}")
    @ResponseBody
    public JsonResult news(@PathVariable Integer id, @RequestBody Movie movie) {
        Movie find = movieService.getOneMovieById(id);
        JsonResult result;
        if (find == null) {
            result = JsonResult.error(404, "没有找到该资讯");
        } else {
            movie.setId(id);
            int i = movieService.updateOneMovie(movie);
            if (i == 1) {
                result = JsonResult.success("修改资讯成功", null);
            } else {
                result = JsonResult.error(400, "修改资讯失败");
            }
        }
        return result;
    }

    /**
     * 删除电影
     *
     * @param id 删除电影的id
     * @return 返回的JSON数据
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public JsonResult delNews(@PathVariable("id") Integer id) {
        Movie find = movieService.getOneMovieById(id);
        JsonResult result;
        if (find == null) {
            result = JsonResult.error(404, "没有找到该电影");
        } else {
            int i = movieService.deleteOneMovie(id);
            if (i == 1) {
                result = JsonResult.success("删除电影成功", null);
            } else {
                result = JsonResult.error(400, "删除电影失败");
            }
        }
        return result;
    }

    @GetMapping("/released")
    @ResponseBody
    public JsonResult getReleased() {
        List<Movie> movies = movieService.selectReleased();
        if (movies!=null){
           return JsonResult.success("查询上映的电影成功",movies);
        }
        return JsonResult.error(404,"查询上映的电影失败");
    }

}
