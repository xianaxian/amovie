package com.ecjtu.amovie.repository;

import com.ecjtu.amovie.entity.Order;
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
public interface OrderRepository {

    @Insert("INSERT INTO `order` (status, user_id, create_time, scene_id, ticket_num, total_price, booked_seated) " + "VALUES (#{status},#{userId},#{createTime},#{sceneId},#{tickleNum},#{totalPrice},#{bookedSeated})")
    int saveOne(Order order);

    @Delete("DELETE FROM `order` WHERE id=#{id} ")
    int delectOne(Integer id);

    @Update("UPDATE `order` SET status=#{status},user_id=#{userId},scene_id=#{sceneId},ticket_num=#{ticketNum} ," +
            "total_price=#{totalPrice},booked_seated=#{bookedSeated} WHERE id=#{id};")
    int updateOne(Order order);


    @Select("SELECT * FROM `order` where id=#{id};")
    Order selectOne(Integer id);


    @Select("SELECT * FROM `order`;")
    List<Order> selectAll();
}
