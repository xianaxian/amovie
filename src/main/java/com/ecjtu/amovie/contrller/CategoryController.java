package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.entity.Category;
import com.ecjtu.amovie.service.CategoryService;
import com.ecjtu.amovie.utils.Json;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xianaixan
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 获取的是没有分页的分类
     * @param response
     * @return
     */
    @GetMapping("/categoriesAll")
    @ResponseBody
    public JsonResult<List<Category>> categories(HttpServletResponse response){
        List<Category> categories = categoryService.getCategories();
        JsonResult<List<Category>> result=JsonResult.success("查询电影分类成功",categories);
        return result;

    }

    /**
     * 查询电影类别列表
     *
     * @param pageNum 页码
     * @param pageSize 每页的大小
     */
    @GetMapping
    @ResponseBody
    public JsonResult<PageInfo<Category>> categories(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        PageInfo<Category> categories = categoryService.getCategoriesByPage(pageNum, pageSize);
        return JsonResult.success("查询电影类别成功", categories);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public JsonResult categories(@PathVariable("id") Integer id) {
        Category category = categoryService.getOneCategory(id);
        JsonResult<Object> result;
        if (category == null) {
            result = JsonResult.error(404, "没有找到分类");
        } else {
            result = JsonResult.success("查询该类别成功", category);
        }
        return result;
    }

    /**
     * 创建类别
     *
     * @param category 类别的嘻嘻
     * @return Jsons数据
     */
    @PostMapping()
    @ResponseBody
    public JsonResult categories(@Validated @RequestBody Category category) {
        int i = categoryService.insertOneCategory(category.getName());
        JsonResult result;
        if (i == 1) {
            result = JsonResult.success("创建类别成功", null);
        } else {
            result = JsonResult.error(400, "创建失败");
        }
        return result;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public JsonResult categories(@PathVariable Integer id, @RequestBody Category category) {
        Category find = categoryService.getOneCategory(id);
        JsonResult result;
        if (find == null) {
            result = JsonResult.error(404, "没有找到该类别");
        } else {
            category.setId(id);
            int i = categoryService.updateOneCategory(category);
            if (i == 1) {
                result = JsonResult.success("修改类别成功", null);
            } else {
                result = JsonResult.error(400, "修改类别失败");
            }
        }
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public JsonResult delCategory(@PathVariable("id") Integer id) {
        Category find = categoryService.getOneCategory(id);
        JsonResult result;
        if (find == null) {
            result = JsonResult.error(404, "没有找到该类别");
        } else {
            int i = categoryService.deleteOneCategory(id);
            if (i == 1) {
                result = JsonResult.success("删除类别成功", null);
            } else {
                result = JsonResult.error(400, "删除类别失败");
            }
        }
        return result;
    }

}
