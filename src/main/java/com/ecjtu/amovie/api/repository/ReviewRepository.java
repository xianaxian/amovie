package com.ecjtu.amovie.api.repository;

import com.ecjtu.amovie.api.entity.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * @author xianaixan
 */
public interface ReviewRepository {
    /**
     * 添加一个评论
     * @param review 评论的信息
     * @return 受影响的行数
     */
    @Insert("INSERT INTO review (content, movie_id, user_id, create_time)" +
            "VALUES (#{content},#{movieId},#{userId},#{createTime});")
    int saveOne(Review review);

    /**
     * 删除
     * @param id 评论的id
     * @return 删除受影响的行数
     */
    @Delete("DELETE FROM review WHERE id = #{id};")
    int delectOne(Integer id);

    /**
     * 更新
     * @param review 更新的评论的信息
     * @return 受影响的行数
     */
    @Update("UPDATE review SET content  = #{content} WHERE id=#{id};")
    int updateOne(Review review);

    /**
     * 通过评论的id查找该评论
     * @param id 评论的id
     * @return 返回该评论的信息
     */
    @Select("SELECT * FROM review where id=#{id}")
    Review selectOne(Integer id);

    /**
     * 查询所有评论的信息
     * @return 所有的评论
     */
    @Select("SELECT * FROM review")
    List<Review> selectAll();

    /**
     * 通过电影的Id查找该电影的评论
     * @param movieId 电影的id
     * @return 该电影的所有评论
     */
    @Select("SELECT review.*,user.nickname FROM review left join user on review.user_id=user.id WHERE movie_id =${movieId} ")
    List<Review> selectByMovie(int movieId);

}
