package com.ecjtu.amovie.repository;

import com.ecjtu.amovie.entity.News;
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

    @Insert("INSERT INTO news (content, create_time) VALUES (#{content},#{createTime})")
    int saveOne(News news);

    @Delete("DELETE FROM news WHERE id = #{id}")
    int deleteOne(Integer id);

    /**
     * 只能更新内容
     * @param news
     * @return
     */
    @Update("UPDATE news SET content  =  #{content} WHERE id=#{id}")
    int updateOne(News news);

    @Select("SELECT * FROM news where id=#{id}")
    News selectOne(Integer id);

    @Select("SELECT * FROM news")
    List<News> selectAll();

}
