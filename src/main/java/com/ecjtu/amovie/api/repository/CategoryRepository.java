package com.ecjtu.amovie.api.repository;

import com.ecjtu.amovie.api.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author xianaixan
 */
@Repository
public interface CategoryRepository {

    @Insert("INSERT INTO category (name) VALUES (#{name});")
    int saveOne(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteOne(Integer id);

    @Update("UPDATE category SET name  = #{name} WHERE id=#{id};")
    int updateOne(Category category);

    @Select("SELECT * FROM category where id=#{id};")
    Category selectOne(Integer id);

    @Select("SELECT * FROM category")
    List<Category> selectAll();



}
