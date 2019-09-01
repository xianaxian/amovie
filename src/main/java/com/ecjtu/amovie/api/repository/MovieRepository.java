package com.ecjtu.amovie.api.repository;

import com.ecjtu.amovie.api.entity.Movie;
import com.ecjtu.amovie.form.MovieResult;
import com.ecjtu.amovie.form.RateListForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @author xianaixan
 */
@Repository
public interface MovieRepository {

    @Insert("INSERT INTO movie (name, duration, directors, actors, release_date, status, plot, poster, country) " + "VALUES (#{name},#{duration},#{directors},#{actors},#{releaseDate},#{status},#{plot},#{poster},#{country});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveOne(Movie movie);

    @Delete("DELETE FROM movie WHERE id = #{id}")
    int deleteOne(Integer id);


    int updateOne(Movie movie);


    /**
     * 查询某个电影的分类的id
     *
     * @param movieId 电影的id
     * @return 电影分类的category的id, 这个查询放在此处有点不合适
     */
    List<Integer> selectCategoryOfMovie(Integer movieId);

    /**
     * 查询单个电影
     *
     * @param id 查询的电影的id
     * @return 电影
     */

    Movie selectOne(Integer id);


    List<Movie> selectAll();


    List<Movie> selectReleased();

    List<MovieResult> released();

    /**
     * 没有使用
     *
     * @param name 查询的名称
     * @return 电影中含有的名称
     */
    List<Movie> selectByName(String name);

    /**
     * 没有使用
     * 根据分类的id查询
     *
     * @param id 分类的id
     * @return 电影的列表
     */
    List<Movie> selectByCategory(Integer id);


    /**
     * 没测试
     * 插入信息到movie_category里面，这里
     *
     * @param movieId    电影的id
     * @param categoryId 分类的id
     * @return 插入的结果.插入成功返回id
     */
    @Insert("INSERT INTO movie_category ( movie_id,category_id) VALUES (#{movieId},#{categoryId});")
    int insertCategoryOne(Integer movieId, Integer categoryId);

    int insertCategories(@Param("movieId") Integer movieId, @Param("ids") List<Integer> ids);

    /**
     * 删除某个电影的所有分类
     * @param movieId 电影的id
     * @return 返回是否删除成功
     */
    @Delete("DELETE FROM movie_category WHERE movie_id = #{id}")
    int deleteCategories(Integer movieId);

    /**
     * 根据条件查询
     * @param conditions 条件
     * @return 查询出的list
     */
    List<Movie> selectByCondition(Map<String,Object> conditions);


    /**
     * 电影页，没有登陆情况下的显示
     * @return 电影的信息
     */
    List<MovieResult> moviePageNoLogin();


    List<MovieResult> moviePageNoLoginCatetgory(Integer integer);


    List<RateListForm> rateList();

    /**
     * 查找评分最佳的六部电影
     * @return
     */
    List<MovieResult> bestMovie();

}
