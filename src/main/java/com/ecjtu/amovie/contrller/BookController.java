package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.Scene;
import com.ecjtu.amovie.api.service.SceneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;

/**
 * @author xianaxian  2019-09-01 20:40
 */
@Controller
public class BookController {
    private final SceneService sceneService;

    public BookController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @GetMapping("/book/{id}")
    public ModelAndView book1(@PathVariable("id") Integer id){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("book1");
        List<Scene> scenes = sceneService.selectByMovieId(id);
        mav.addObject("scenes", scenes);
        return mav;
    }
    @GetMapping("/book2/{id}")
    public ModelAndView book2(@PathVariable("id") Integer id){
        String selled = sceneService.getSelled(id);
        Scene scene = sceneService.getOneScene(id);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("book2");
        mav.addObject("selled", selled);
        mav.addObject("scene",scene);
        return mav;
    }
}
