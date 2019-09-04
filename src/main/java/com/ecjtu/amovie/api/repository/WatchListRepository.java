package com.ecjtu.amovie.api.repository;

import com.ecjtu.amovie.api.entity.Movie;
import com.ecjtu.amovie.form.MovieResult;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;
import java.util.List;
/**
 * @author 汪慧贤
 */
@Repository
public interface WatchListRepository {

    /**
     * 查询电影的所有分类，返回值是分类的id
     * @param movieId 电影的id
     * @return 返回值是分类的ID的List
     */
    List<Integer> selectCategoryOfMovie(Integer movieId);

    /**
     * 通过用户的id查找他的观看列表中的电影
     * @param userid 用户的id
     * @return 返回的是指定类型的电影信息，比Movie对象含有的信息更多
     */

    List<MovieResult> selectWatchList(int userid);

    /**
     * 插入一个信息到watchlist中
     * @param movieId 电影的id
     * @param userId 用户的id
     * @param date 时间
     * @return 返回受影响的行数
     */
    @Insert("INSERT INTO watch_list (movie_id, user_id, create_time) VALUES (#{movieId},#{userId},#{date});")
    int insertOne(@Param("movieId") int movieId, @Param("userId") int userId, @Param("date")Date date);

    /**
     * 因为数据库没有将电影id和用户id作为主键，所以所有的插入前要判断是否存在
     * @param movieId 电影的id
     * @param userId 用户的id
     * @return 返回查询到的行数
     */
    @Select("SELECT count(*) FROM watch_list  WHERE user_id=#{userId} and movie_id= #{movieId};")
    int isExist(@Param("movieId") int movieId, @Param("userId") int userId);

    /**
     * 从观看列表中删除一条观看记录
     * @param movieId 电影的Id
     * @param userId 用户的id
     * @return 返回的是删除语句受影响的行数
     */
    @Delete("DELETE  FROM watch_list  WHERE user_id=#{userId} and movie_id= #{movieId};")
    int deleleOne(@Param("movieId") int movieId, @Param("userId") int userId);

    /**
     * 查询某个用户观看列表中的所有电影的id
     * @param userId 用户的id
     * @return 返回的是电影的观看列表中的movies的id
     */
    @Select("SELECT movie_id FROM watch_list where user_id=#{userId}")
    List<Integer> selectByUser(int userId);
}
