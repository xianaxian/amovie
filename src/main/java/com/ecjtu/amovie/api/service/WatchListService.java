package com.ecjtu.amovie.api.service;

import com.ecjtu.amovie.api.entity.Movie;
import com.ecjtu.amovie.api.repository.MovieRepository;
import com.ecjtu.amovie.api.repository.WatchListRepository;
import com.ecjtu.amovie.form.MovieResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xianaxian  2019-08-28 20:19
 */
@Service
public class WatchListService {
    private final WatchListRepository watchListRepository;

    public WatchListService(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    /**
     * 获取用于的播放列表
     * @param pageNum 页码
     * @param pageSize 每页的数量
     * @param userId 用户的id
     * @return 分页的用户的播放列表中的数据
     */
    public PageInfo<MovieResult> watchlist(int pageNum, int pageSize, int userId){
        return PageHelper.startPage(pageNum,pageSize).doSelectPageInfo(()->watchListRepository.selectWatchList(userId));
    }

    /**
     * 插入到播放列表
     * @param movieId 电影的id
     * @param userId 用户的id
     * @return 返回插入是否成功
     */
    public int  insertWatchlist(int movieId,int userId){
        int exist = watchListRepository.isExist(movieId, userId);
        if (exist!=0){
            throw new RuntimeException("插入播放列表时重复添加");
        }
        return watchListRepository.insertOne(movieId, userId, new Date());
    }

    /**
     * 从播放列表删除一个电影
     * @param movieId 电影的id
     * @param userId 用户的id
     * @return 删除是否成功
     */
    public int deleteWatchlist(int movieId,int userId){
        return watchListRepository.deleleOne(movieId, userId);
    }

    public List<Integer> selectByUser(int userId){
        return watchListRepository.selectByUser(userId);
    }

}
