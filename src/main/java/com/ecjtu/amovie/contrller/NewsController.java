package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.News;
import com.ecjtu.amovie.api.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xianaixan
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    /**
     * 获取资讯列表
     *
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return mav
     */
    @GetMapping
    @ResponseBody
    public ModelAndView news(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        PageInfo<News> news = newsService.getNewsByPage(pageNum, pageSize);
        ModelAndView mav= new ModelAndView();
        mav.addObject("news", news);
        mav.setViewName("news-list");
        return mav;
    }

    /**
     * 获取资讯
     * @param id 资讯id
     * @return mav
     */
    @GetMapping("/{id}")
    public ModelAndView getANews(@PathVariable("id") Integer id) {
        News aNews = newsService.getOneNews(id);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("news");
        mav.addObject("aNew",aNews);
        return mav;
    }


}
