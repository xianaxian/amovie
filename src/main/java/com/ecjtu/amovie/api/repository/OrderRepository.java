package com.ecjtu.amovie.api.repository;

import com.ecjtu.amovie.api.entity.Order;
import com.ecjtu.amovie.form.OrderResult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 没测试
 * @author xianaixan
 */
@Repository
public interface OrderRepository {

    /**
     * 插入一个订单（参数对象，id插入后赋值）
     * @param order 订单对象
     * @return 返回受影响的行数
     */
    @Options(keyColumn = "id",keyProperty = "id",useGeneratedKeys = true)
    @Insert("INSERT INTO `order` (status, user_id, create_time, scene_id, ticket_num, total_price, booked_seated) " +
            "VALUES (#{status},#{userId},#{createTime},#{sceneId},#{ticketNum},#{totalPrice},#{bookedSeated})")
    int saveOne(Order order);


    /**
     * 删除单个订单
     * @param id 订单的id
     * @return 返回删除受影响的行数
     */
    @Delete("DELETE FROM `order` WHERE id=#{id} ")
    int delectOne(Integer id);

    /**
     * 更新单个订单（没用使用）
     * @param order 订单的信息
     * @return 返回更新受影响的行数
     */
    @Update("UPDATE `order` SET status=#{status},user_id=#{userId},scene_id=#{sceneId},ticket_num=#{ticketNum} ," +
            "total_price=#{totalPrice},booked_seated=#{bookedSeated} WHERE id=#{id};")
    int updateOne(Order order);


    /**
     * 查询单个订单
     * @param id  订单的id
     * @return 订单
     */
    @Select("SELECT * FROM `order` where id=#{id}")
    Order selectOne(Integer id);

    /**
     * 查询所有订单的信息
     * @return 所有订单
     */
    @Select("SELECT * FROM `order`")
    List<Order> selectAll();

    /**
     * 查询单个订单的信息（供前台页面使用）
     * @param id 订单的id
     * @return 返回的指定类型的订单的信息
     */

    @Select("SELECT `order`.* , scene.movie_name movieName,scene.showtime FROM `order` left join scene on `order`.scene_id=scene.id where `order`.id=#{id} ")
    OrderResult selectInfo(int id);

    /**
     * 通过用户的id查找他所有的订单信息（供前台页面使用）
     * @param uId 用户的id
     * @return 订单信息
     */
    @Select("SELECT `order`.* , scene.movie_name movieName,scene.showtime FROM `order` left join scene on `order`.scene_id=scene.id where `order`.user_id=#{uId} ")
    List<OrderResult> selectInfoByUser(int uId);

}
