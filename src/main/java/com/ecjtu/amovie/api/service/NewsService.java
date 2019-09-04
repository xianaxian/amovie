package com.ecjtu.amovie.api.service;

import com.ecjtu.amovie.api.entity.News;
import com.ecjtu.amovie.api.repository.NewsRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xianaixan
 */
@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    /**
     * 获取资讯的分页
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页的数据
     */
    public PageInfo<News> getNewsByPage(int pageNum, int pageSize){
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(newsRepository::selectAll);
    }


    /**
     * 插入一个咨询
     * @param news 插入的资讯
     * @return 是否插入成功
     */
    public int insertOneNews(News news){
        news.setCreateTime(new Date());
        return newsRepository.saveOne(news);
    }

    /**
     * 通过id查询到一个资讯
     * @param id news的id
     * @return 查询到的电影
     */
    public News getOneNews(Integer id){
        return newsRepository.selectOne(id);
    }

    /**
     * 通过id删除一个资讯
     * @param id news的id
     * @return 是否删除成功
     */

    public int deleteOneNews(Integer id){
        return newsRepository.deleteOne(id);
    }

    /**
     * 更新一个资讯
     * @param news news的信息
     * @return 是否更新成功
     */
    public int updateOneNews(News news){
        return newsRepository.updateOne(news);
    }

    public List<News> getLatestNews(){
        return newsRepository.getLastestNews();
    }
}
