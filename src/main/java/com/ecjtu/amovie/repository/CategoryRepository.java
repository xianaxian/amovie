package com.ecjtu.amovie.repository;

import com.ecjtu.amovie.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    @Select("SELECT * FROM category;")
    List<Category> selectAll();
}
