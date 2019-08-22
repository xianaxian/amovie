package com.ecjtu.amovie.repository;


import com.ecjtu.amovie.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author xianaixan
 */
@Repository
public interface UserRepository {
    @Insert("insert into user (nickname, email, password, salt, phone, gender, role) VALUES (#{nickname},#{email},#{password},#{salt},#{phone},#{gender},#{role})")
    int saveOne(User user);

    @Select("select * from user where email=#{email}")
    User findOneByEmail(String email);


    @Select("select count(*) from user where email=#{email}")
    int isEmailExists(String email);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    @Update("update user set nickname=#{nickname},email=#{email},password=#{password},phone=#{phone},gender=#{gender},role=#{role} where id=#{id}")
    int updateOne(User user);
}
