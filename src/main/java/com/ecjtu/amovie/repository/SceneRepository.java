package com.ecjtu.amovie.repository;

import com.ecjtu.amovie.entity.Scene;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 没测试
 */
@Repository
public interface SceneRepository {

    @Insert("INSERT INTO scene (movie_id, movie_name, price, seat_num, showtime, booked_seat) " +
            "VALUES (#{movieId},#{movieName},#{price},#{seatNum},#{showTime},#{bookedSeat});")
    int saveOne(Scene scene);

    @Delete("DELETE FROM scene WHERE id = #{id}")
    int delectOne(Integer id);

    @Update("UPDATE scene SET movie_id=#{movieId},movie_name=#{movieName},price=#{price} " +
            ",showtime=#{showTime},booked_seat=#{bookedSeat}  WHERE id=#{id} ;")
    int updateOne(Scene scene);

    @Select("SELECT * FROM scene where id=#{id};")
    Scene selectOne(Integer id);

    @Select("SELECT * FROM scene;")
    List<Scene> selectAll();

}
