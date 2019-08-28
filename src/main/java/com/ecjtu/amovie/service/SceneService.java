package com.ecjtu.amovie.service;

import com.ecjtu.amovie.entity.Movie;
import com.ecjtu.amovie.entity.Scene;
import com.ecjtu.amovie.repository.MovieRepository;
import com.ecjtu.amovie.repository.SceneRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xianaixan
 */
@Service
public class SceneService {

    private final SceneRepository sceneRepository;
    private final MovieRepository movieRepository;

    public SceneService(SceneRepository sceneRepository, MovieRepository movieRepository) {
        this.sceneRepository = sceneRepository;
        this.movieRepository = movieRepository;
    }


    /**
     * 查询场次列表（分页）
     * @param pageNum  页码
     * @param pageSize 一页的大小
     * @return 查询到已经分页的数据
     */

    public PageInfo<Scene> getSceneByPage(int pageNum, int pageSize){
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->sceneRepository.selectAll());
    }

    /**
     * 插入一个场次
     * @param scene 插入的场次
     * @return 是否插入成功，成功返回1
     */

    public int insertOneScene(Scene scene){
        Integer movieId = scene.getMovieId();
        Movie movie = movieRepository.selectOne(movieId);
        scene.setMovieName(movie.getName());
        return sceneRepository.saveOne(scene);
    }

    /**
     * 通过id查询到一个场次
     * @param id 该场次的id
     * @return 一个场次的对象
     */
    public Scene getOneScene(Integer id){
        return sceneRepository.selectOne(id);
    }

    /**
     * 通过id删除一个场次
     * @param id 该场次的id
     * @return 是否删除成功，删除成功返回1
     */

    public int deleteOneScene(Integer id){
        return sceneRepository.delectOne(id);
    }

    /**
     * 更新一个场次的信息
     * @param scene 更新的场次的信息
     * @return 更新是否成功，更新成功返回1
     */
    public int updateOneScene(Scene scene) {
        return sceneRepository.updateOne(scene);
    }

    public List<Scene> selectByMovieId(Integer id){
        return sceneRepository.selectByMovieId(id);
    }
}
