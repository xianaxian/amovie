package com.ecjtu.amovie.service;

import com.ecjtu.amovie.entity.Category;
import com.ecjtu.amovie.entity.Movie;
import com.ecjtu.amovie.repository.CategoryRepository;
import com.ecjtu.amovie.repository.MovieRepository;
import com.ecjtu.amovie.utils.Json;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 分页获取电影列表
     * @param pageNum
     * @param pageSize
     * @return 分页的信息
     */
    public Page<Movie> getMoviesByPage(int pageNum, int pageSize){
        Page<Movie> movies = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> movieRepository.selectAll());
        for (Movie movie : movies) {
            String categoryId = movie.getCategoryId();
            if (StringUtils.isEmpty(categoryId)){
                continue;
            }
            List<Integer> ids = Json.parseArray(categoryId, Integer.class);
            List<Category> temp = new ArrayList<>();
            for (Integer id : ids) {
                Category category = categoryRepository.selectOne(id);
                temp.add(category);
            }
            movie.setList(temp);
        }
        return movies;
    }


    /**
     * 获取某个电影
     * @param id 电影的ID
     * @return
     */
    public Movie getOneMovieById(Integer id){
        Movie movie = movieRepository.selectOne(id);
        String categoryId = movie.getCategoryId();
        if (!StringUtils.isEmpty(categoryId)){
            List<Integer> ids = Json.parseArray(categoryId, Integer.class);
            List<Category> temp = new ArrayList<>();
            for (Integer integer : ids) {
                temp.add(categoryRepository.selectOne(integer));
            }
            movie.setList(temp);
        }
        return movie;
    }

    /**
     * 插入一个分类
     * @param movie
     * @return
     */
    public int insertOneMovie(Movie movie){
        return movieRepository.saveOne(movie);
    }

    public int updateOneMovie(Movie movie) {
        return movieRepository.updateOne(movie);
    }



    public int deleteOneMovie(Integer id){
        return movieRepository.deleteOne(id);
    }
}
