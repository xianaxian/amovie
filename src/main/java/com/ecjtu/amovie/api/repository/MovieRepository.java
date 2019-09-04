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

    /**
     * 插入一个电影
     * @param movie 插入的电影
     * @return 返回语句执行受影响的行数
     */
    @Insert("INSERT INTO movie (name, duration, directors, actors, release_date, status, plot, poster, country) " + "VALUES (#{name},#{duration},#{directors},#{actors},#{releaseDate},#{status},#{plot},#{poster},#{country});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveOne(Movie movie);


    /**
     * 删除一个电影
     * @param id 电影的id
     * @return 受影响的行数
     */
    @Delete("DELETE FROM movie WHERE id = #{id}")
    int deleteOne(Integer id);


    /**
     * 更新一个电影的信息
     * @param movie 电影的信息
     * @return 返回语句执行受影响的行数
     */
    int updateOne(Movie movie);

    /**
     * 查询所有电影
     * @return 查询出的电影对象
     */
    List<Movie> selectAll();


    /**
     * 查询单个电影
     * @param id 查询的电影的id
     * @return 电影
     */

    Movie selectOne(Integer id);


    /**
     * 查询所有上映的电影（后台页面添加场次时使用）
     * @return 返回的是所有上映的电影
     */
    List<Movie> selectReleased();

    /**
     * 查询某个电影的分类的id
     * @param movieId 电影的id
     * @return 电影分类的category的id, 这个查询放在此处有点不合适
     */
    List<Integer> selectCategoryOfMovie(Integer movieId);

    /**
     * 查询所有电影的信息（给前台页面）
     * @return 返回的是需要的电影的信息
     */
    List<MovieResult> released();

    /**
     * 根据名字查询电影的(没有使用？？)
     * @param name 查询的名称
     * @return 电影中含有的名称
     */
    List<Movie> selectByName(String name);

    /**
     * 根据分类的id查询（好像是没有使用）
     * @param id 分类的id
     * @return 电影的列表
     */
    List<Movie> selectByCategory(Integer id);


    /**
     * 插入信息到movie_category里面
     * @param movieId    电影的id
     * @param categoryId 分类的id
     * @return 插入的结果.插入成功返回id
     */
    @Insert("INSERT INTO movie_category ( movie_id,category_id) VALUES (#{movieId},#{categoryId});")
    int insertCategoryOne(Integer movieId, Integer categoryId);

    /**
     * 批量插入信息到movie_category里面
     * @param movieId 电影的id
     * @param ids 分类的id
     * @return 返回受影响的行数
     */
    int insertCategories(@Param("movieId") Integer movieId, @Param("ids") List<Integer> ids);

    /**
     * 删除某个电影的所有分类
     * @param movieId 电影的id
     * @return 返回是否删除成功
     */
    @Delete("DELETE FROM movie_category WHERE movie_id = #{id}")
    int deleteCategories(Integer movieId);

    /**
     * 根据条件查询（没有使用）
     * @param conditions 条件
     * @return 查询出的list
     */
    List<Movie> selectByCondition(Map<String,Object> conditions);


    /**
     * 电影页，没有登陆情况下的显示所有电影信息
     * @return 电影的信息
     */
    List<MovieResult> moviePageNoLogin();


    /**
     * moviesList页面，根据分类查询查询出电影
     * @param integer 分类的Id
     * @return 该分类下的电影
     */
    List<MovieResult> moviePageNoLoginCatetgory(Integer integer);

    /**
     * moviesList页面搜索框中的搜索
     * @param column 搜索的行
     * @param value 值为
     * @return 返回符合条件的电影信息
     */
    List<MovieResult> search( String column, String value);

    /**
     * 查找评分最佳的六部电影
     * @return 评分最佳的六部电影
     */
    List<MovieResult> bestMovie();

    /**
     * 电影的排行榜
     * @return 查询电影中评分最高的20条
     */
    List<RateListForm> rateList();



}
