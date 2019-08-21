package com.ecjtu.amovie.repository;

import com.ecjtu.amovie.entity.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ReviewRepository {

    @Insert("INSERT INTO review (content, movie_id, user_id, create_time)" +
            "VALUES (#{content},#{movieId},#{userId},#{createTime});")

    int saveOne(Review review);

    @Delete("DELETE FROM review WHERE id = #{id};")
    int delectOne(Integer id);

    @Update("UPDATE review SET content  = #{content} WHERE id=#{id};")
    int updateOne(Review review);

    @Select("SELECT * FROM review where id=#{id}")
    Review selectOne(Integer id);

    @Select("SELECT * FROM review")
    List<Review> selectAll();

}
