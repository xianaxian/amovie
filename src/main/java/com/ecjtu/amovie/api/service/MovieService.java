package com.ecjtu.amovie.api.service;

import com.ecjtu.amovie.api.entity.Movie;
import com.ecjtu.amovie.api.repository.MovieRepository;
import com.ecjtu.amovie.form.MovieResult;
import com.ecjtu.amovie.form.RateListForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xianaixan
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * 分页获取电影列表
     *
     * @param pageNum  页码
     * @param pageSize 每页的大小
     * @return 分页的信息
     */
    public PageInfo<Movie> getMoviesByPage(int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(movieRepository::selectAll);
    }

    public PageInfo<Movie> getMoviesByPage(int pageNum, int pageSize, int category) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> movieRepository.selectByCategory(category));
    }

    /**
     * 查询电影排行版的前20
     *
     * @return 前20个电影的信息
     */
    public List<RateListForm> getRankingList() {
        return movieRepository.rateList();
    }

    public PageInfo<MovieResult> moviePageNoLogin(int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(movieRepository::moviePageNoLogin);
    }

    public PageInfo<MovieResult> moviePageNoLoginCatetgory(int pageNum, int pageSize, int category) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> movieRepository.moviePageNoLoginCatetgory(category));
    }

    public PageInfo<MovieResult> search(int pageNum, int pageSize, int searchType, String value) {
        String column;
        String[] arr = {"name", "directors", "actors", "country"};
        column = (searchType > 0 && searchType < 5) ? arr[searchType-1] : "";
        if (StringUtils.isEmpty(column)) {
            throw new RuntimeException("搜索没有选择合适的类别");
        }
        String finalColumn = column;
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> movieRepository.search(finalColumn, value));
    }

    /**
     * 获取某个电影
     *
     * @param id 电影的ID
     * @return 某个电影
     */
    public Movie getOneMovieById(Integer id) {
        return movieRepository.selectOne(id);
    }


    /**
     * 插入一个电影
     *
     * @param movie 插入的电影
     * @return 返回的是受影响的行数，1为插入成功
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int insertOneMovie(Movie movie) {
        int i = movieRepository.saveOne(movie);
        int movieId = movie.getId();
        Integer[] cIds = movie.getCategoryIds();
        movieRepository.insertCategories(movieId, Arrays.asList(cIds));
        return i;
    }


    /**
     * 此处更新电影的类别的时候，由于多对多关系，直接先删除movie_category中与该电影相关的数据再重新插入,目前没有更好的想法
     *
     * @param movie 更新的电影的信息
     * @return 返回是否更新成功
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateOneMovie(Movie movie) {
        if (movie.getCategoryIds() == null) {
            return movieRepository.updateOne(movie);
        }
        Integer[] ids = movie.getCategoryIds();
        Integer movieId = movie.getId();
        movieRepository.deleteCategories(movieId);
        movieRepository.insertCategories(movieId, Arrays.asList(ids));
        return movieRepository.updateOne(movie);
    }


    /**
     * 删除一个电影，顺便把movie_category表中的相关数据删除
     *
     * @param id 电影的id
     * @return 返回删除电影表的受影响的行数, 1表示删除成功, 此处写的很奇怪
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteOneMovie(Integer id) {
        movieRepository.deleteCategories(id);
        return movieRepository.deleteOne(id);
    }

    public List<Movie> selectReleased() {
        return movieRepository.selectReleased();
    }

    /**
     * 没有测试(最终也没有用)
     * 据一些条件查询电影
     *
     * @param conditions <属性的名称,属性的值>
     * @return 按照条件查询到的东西
     */
    public List<Movie> selectByCondition(Map<String, Object> conditions) {
        return movieRepository.selectByCondition(conditions);
    }


    public List<MovieResult> bestMovie() {
        return movieRepository.bestMovie();
    }

    public List<MovieResult> released() {
        return movieRepository.released();
    }

}
