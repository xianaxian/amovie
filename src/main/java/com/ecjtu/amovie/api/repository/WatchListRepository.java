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
    List<Integer> selectCategoryOfMovie(Integer movieId);

    List<MovieResult> selectWatchList(int userid);

    @Insert("INSERT INTO watch_list (movie_id, user_id, create_time) VALUES (#{movieId},#{userId},#{date});")
    int insertOne(@Param("movieId") int movieId, @Param("userId") int userId, @Param("date")Date date);

    @Select("SELECT count(*) FROM watch_list  WHERE user_id=#{userId} and movie_id= #{movieId};")
    int isExist(@Param("movieId") int movieId, @Param("userId") int userId);

    @Delete("DELETE  FROM watch_list  WHERE user_id=#{userId} and movie_id= #{movieId};")
    int deleleOne(@Param("movieId") int movieId, @Param("userId") int userId);

    @Select("SELECT movie_id FROM watch_list where user_id=#{userId}")
    List<Integer> selectByUser(int userId);
}
