package com.ecjtu.amovie.api.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 汪慧贤
 */
@Repository
public interface ScoreRepository {
    /**
     * 插入一条评分的数据
     * @param mId 电影的id
     * @param uId 用户的id
     * @param score 用户给电影的评分
     * @return 插入结果，1是真
     */
    @Insert("INSERT INTO score (movie_id, user_id, score) VALUES (#{mId},#{uId},#{score});")
    int insertOne(@Param("mId") int mId, @Param("uId") int uId, @Param("score") int score);

    /**
     * 判断是否已经评分
     * @param movieId 电影的id
     * @param userId 用户的id
     * @return 是否插入成功
     */
    @Select("SELECT count(*) FROM score  WHERE user_id=#{userId} and movie_id= #{movieId};")
    int isExist(@Param("movieId") int movieId, @Param("userId") int userId);


}
