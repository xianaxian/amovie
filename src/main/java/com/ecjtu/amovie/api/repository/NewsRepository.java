package com.ecjtu.amovie.api.repository;

import com.ecjtu.amovie.api.entity.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xianaixan
 */
@Repository
public interface NewsRepository {
    /**
     * 插入资讯
     * @param news 资讯
     * @return 返回插入时受影响的行数
     */
    @Insert("INSERT INTO news (title,content, create_time) VALUES (#{title},#{content},#{createTime})")
    int saveOne(News news);

    /**
     * 删除资讯
     * @param id 资讯的id
     * @return 返回受影响的行数
     */
    @Delete("DELETE FROM news WHERE id = #{id}")
    int deleteOne(Integer id);

    /**
     * 更新资讯（只能更新内容和标题）
     * @param news 更新后的资讯
     * @return 返回更新语句受影响的行数
     */
    @Update("UPDATE news SET title=#{title}, content  =  #{content} WHERE id=#{id}")
    int updateOne(News news);


    /**
     * 通过id查询资讯的信息
     * @param id 资讯的id
     * @return 返回相关的对象
     */
    @Select("SELECT * FROM news where id=#{id}")
    News selectOne(Integer id);

    /**
     * 查找所有资讯
     * @return 所有资讯
     */
    @Select("SELECT * FROM news")
    List<News> selectAll();

    /**
     * 获取最新的三条资讯（用于首页）
     * @return 最新的三条资讯
     */
    @Select("SELECT * FROM news order by create_time desc limit 3")
    List<News> getLastestNews();
}
