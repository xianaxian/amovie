package com.ecjtu.amovie.api.repository;


import com.ecjtu.amovie.api.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xianaixan
 */
@Repository
public interface UserRepository {
    /**
     * 插入一个用户信息
     * @param user 用户的信息
     * @return 插入受影响的行数
     */
    @Insert("insert into user (nickname, email, password, salt, phone, gender, role) VALUES (#{nickname},#{email},#{password},#{salt},#{phone},#{gender},#{role})")
    int saveOne(User user);

    /**
     * 通过邮箱查找用户（登录时使用）
     * @param email 用户的email
     * @return 该用户
     */
    @Select("select * from user where email=#{email}")
    User findOneByEmail(String email);

    /**
     * 注册是判断用户的邮箱是否使用
     * @param email 用户的邮箱
     * @return 返回查询到的行数
     */

    @Select("select count(*) from user where email=#{email}")
    int isEmailExists(String email);

    /**
     * 通过用户的Id查找用户
     * @param id 用户的id
     * @return 返回该用户
     */
    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    /**
     * 更新一个用户的信息
     * @param user 用户的更新后的信息
     * @return 更新影响的行数
     */

    @Update("update user set nickname=#{nickname},email=#{email},password=#{password},phone=#{phone},gender=#{gender},role=#{role} where id=#{id}")
    int updateOne(User user);
}
