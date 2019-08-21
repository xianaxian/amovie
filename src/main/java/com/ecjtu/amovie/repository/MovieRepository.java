package com.ecjtu.amovie.repository;

import com.ecjtu.amovie.entity.Movie;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository {
    @Insert("INSERT INTO movie (name, duration, directors, actors, release_date, category_id, status, plot, poster, country) " +
            "VALUES (#{name},#{duration},#{directors},#{actors},#{releaseDate},#{categoryId},#{status},#{plot},#{poster},#{country});")
    int saveOne(Movie movie);

    @Delete("DELETE FROM movie WHERE id = #{id}")
    int deleteOne(Integer id);

//    @Update("UPDATE movie SET name=#{name},duration=#{duration},directors=#{directors},actors=#{actors},release_date=#{releaseDate}," +
//            "category_id =#{categoryId}  WHERE id=#{id} ")
    int updateOne(Movie movie);


    @Select("SELECT * FROM movie where id=#{id}")
    Movie selectOne(Integer id);

    @Select("SELECT * FROM movie")
    List<Movie> selectAll();

    @Select("SELECT * FROM movie where name like #{name}")
    List<Movie> selectByName(String name);

}
