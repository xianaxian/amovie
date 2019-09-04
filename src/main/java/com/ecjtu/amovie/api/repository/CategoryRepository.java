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

    /**
     * 保存一个分类
     * @param category 分类的信息
     * @return 语句执行受影响的行数
     */
    @Insert("INSERT INTO category (name) VALUES (#{name});")
    int saveOne(Category category);


    /**
     * 删除一个分类
     * @param id 分类的id
     * @return 删除语句执行受影响的行数
     */
    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteOne(Integer id);

    /**
     * 更新一个分类
     * @param category 更新的分类的信息
     * @return 更新语句执行受影响的行数
     */
    @Update("UPDATE category SET name  = #{name} WHERE id=#{id};")
    int updateOne(Category category);

    /**
     * 通过id查找一个分类
     * @param id 分类的id
     * @return 返回的是查询出的对象
     */
    @Select("SELECT * FROM category where id=#{id};")
    Category selectOne(Integer id);

    /**
     * 查询分类的信息
     * @return 返回的是查询到的分类的所有对象
     */

    @Select("SELECT * FROM category")
    List<Category> selectAll();



}
