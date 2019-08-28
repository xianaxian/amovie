package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.News;
import com.ecjtu.amovie.api.service.NewsService;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xianaixan
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;


    /**
     * 获取资讯列表
     *
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Json数据
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
     *
     * @param id 咨询id
     * @return Json数据
     */
    @GetMapping("/{id}")
    public ModelAndView getANews(@PathVariable("id") Integer id) {
        News aNews = newsService.getOneNews(id);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("news");
        mav.addObject("aNew",aNews);
        return mav;
    }

    /**
     * 创建资讯
     *
     * @param news 资讯
     * @return 返回JSON数据
     */
    @PostMapping()
    @ResponseBody
    public JsonResult news(@RequestBody News news) {
        int i = newsService.insertOneNews(news);
        JsonResult result;
        if (i == 1) {
            result = JsonResult.success("创建咨询成功", null);
        } else {
            result = JsonResult.error(400, "创建失败");
        }
        return result;
    }

}
