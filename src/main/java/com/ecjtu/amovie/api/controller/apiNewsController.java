package com.ecjtu.amovie.api.controller;

import com.ecjtu.amovie.entity.News;
import com.ecjtu.amovie.service.NewsService;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xianaixan
 */
@CrossOrigin
@Controller
@RequestMapping("/api/news")
public class apiNewsController {
    @Autowired
    private NewsService newsService;


    /**
     * 获取资讯列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    @ResponseBody
    public JsonResult<PageInfo<News>> news(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        PageInfo<News> news = newsService.getNewsByPage(pageNum, pageSize);
        return JsonResult.success("查询电影类别成功", news);
    }

    /**
     * 获取资讯
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public JsonResult news(@PathVariable("id") Integer id) {
        News news = newsService.getOneNews(id);
        JsonResult<Object> result;
        if (news == null) {
            result = JsonResult.error(404, "没有找到资讯");
        } else {
            result = JsonResult.success("查询该资讯成功", news);
        }
        return result;
    }

    /**
     * 创建资讯
     *
     * @param news
     * @return
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


    /**
     * 更新资讯
     *
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public JsonResult news(@PathVariable Integer id, @RequestBody News news) {
        News find = newsService.getOneNews(id);
        JsonResult result;
        if (find == null) {
            result = JsonResult.error(404, "没有找到该资讯");
        } else {
            news.setId(id);
            int i = newsService.updateOneNews(news);
            if (i == 1) {
                result = JsonResult.success("修改资讯成功", null);
            } else {
                result = JsonResult.error(400, "修改资讯失败");
            }
        }
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public JsonResult delNews(@PathVariable("id") Integer id) {
        News find = newsService.getOneNews(id);
        JsonResult result;
        if (find == null) {
            result = JsonResult.error(404, "没有找到该资讯");
        } else {
            int i = newsService.deleteOneNews(id);
            if (i == 1) {
                result = JsonResult.success("删除资讯成功", null);
            } else {
                result = JsonResult.error(400, "删除资讯失败");
            }
        }
        return result;
    }
}
