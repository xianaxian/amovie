package com.ecjtu.amovie.service;

import com.ecjtu.amovie.entity.Category;
import com.ecjtu.amovie.repository.CategoryRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xianaixan
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * 分页获取分类
     * @param pageNum 页码
     * @param pageSize 每页的大小
     * @return 分页的信息
     */
    public Page<Category>  getCategoriesByPage(int pageNum,int pageSize){
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> categoryRepository.selectAll());

    }

    /**
     * 获取全部分类
     * @return
     */
    public List<Category> getCategories(){
        return  categoryRepository.selectAll();
    }

    /**
     * 获取某个分类
     * @param id 分类的ID
     * @return
     */
    public Category getOneCategory(Integer id){
        return categoryRepository.selectOne(id);
    }

    /**
     * 插入一个分类
     * @param name
     * @return
     */
    public int insertOneCategory(String name){
        Category category=new Category();
        category.setName(name);
        return categoryRepository.saveOne(category);
    }

    /**
     * 修改一个分类
     * @param category 修改的分类
     * @return
     */
    public int updateOneCategory(Category category){
        return categoryRepository.updateOne(category);
    }

    public int deleteOneCategory(Integer id){
        return categoryRepository.deleteOne(id);
    }

}
