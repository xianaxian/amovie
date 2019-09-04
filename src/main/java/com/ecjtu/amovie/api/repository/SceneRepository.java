package com.ecjtu.amovie.api.repository;

import com.ecjtu.amovie.api.entity.Scene;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 没测试
 * @author xianaixan
 */
@Repository
public interface SceneRepository {

    /**
     * 保存电影场次的信息
     * @param scene 电影场次对象
     * @return 返回受影响的行数
     */

    @Insert("INSERT INTO scene (movie_id, movie_name, price, seat_num, showtime, booked_seat) " +
            "VALUES (#{movieId},#{movieName},#{price},#{seatNum},#{showtime},#{bookedSeat});")
    int saveOne(Scene scene);

    /**
     * 删除一个电影场次的信息
     * @param id 电影的id
     * @return 删除受影响的行数
     */
    @Delete("DELETE FROM scene WHERE id = #{id}")
    int delectOne(Integer id);

    /**
     * 更新的场次的信息
     * @param scene 场次的信息
     * @return 更新受影响的行数
     */
    int updateOne(Scene scene);

    /**
     * 查询一个电影场次信息
     * @param id 电影的Id
     * @return 电影的场次对象
     */
    @Select("SELECT * FROM scene where id=#{id}")
    Scene selectOne(Integer id);

    /**
     * 查询所有的电影场次信息
     * @return 电影的场次信息
     */
    @Select("SELECT * FROM scene")
    List<Scene> selectAll();

    /**
     * 查询某个电影的所有场次信息
     * @param id 电影的id
     * @return 返回电影的场次
     */
    @Select("select * from scene where movie_id=#{id}")
    List<Scene> selectByMovieId(Integer id);

    /**
     * 查询出某个场次所有定出的座位
     * @param id 场次的id
     * @return String类型的定出的票如 ["A2","A3"]
     */
    @Select("SELECT booked_seat FROM scene where id=#{id};")
    String selectSelled(Integer id);

}
