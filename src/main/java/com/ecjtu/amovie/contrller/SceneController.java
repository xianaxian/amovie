package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.entity.Scene;
import com.ecjtu.amovie.service.SceneService;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xianaixan
 */
@Controller
public class SceneController {
    private final SceneService sceneService;

    public SceneController(SceneService sceneService) {
        this.sceneService = sceneService;
    }


    /**
     * 查询场次列表
     *
     * @param pageNum  页码，默认是1
     * @param pageSize 每页大小，默认20
     * @return 返回的是JSON数据
     */
    @GetMapping("/scenes")
    @ResponseBody
    public JsonResult scenes(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        PageInfo<Scene> scenes = sceneService.getSceneByPage(pageNum, pageSize);
        return JsonResult.success("查询电影类别成功", scenes);
    }


    /**
     * 获取某个场次的信息
     *
     * @param id 场次的id
     * @return 查询成功返回JsonResult(code = 200, 查询的信息, 查询出的scene对象), 失败返回JsonResult(code = 404, 失败信息, null)
     */
    @GetMapping("/scenes/{id}")
    @ResponseBody
    public JsonResult scenes(@PathVariable("id") Integer id) {
        Scene scene = sceneService.getOneScene(id);
        JsonResult<Object> result;
        if (scene == null) {
            result = JsonResult.error(404, "没有找到场次");
        } else {
            result = JsonResult.success("查询该场次成功", scene);
        }
        return result;
    }


    /**
     * 查询某个电影的所有场次
     *
     * @param id 电影的id
     * @return 返回的JsonResult, 查询出多个场次返回JsonResult(code = 200, 查询结果的的提示信息 ， 查询出的一个电影的一个或者多个场次)
     * 如果某个电影没有场次则返回JsonResult(code=200,查询结果的的提示信息，null)
     */
    @GetMapping("/movies/{id}/scenes")
    @ResponseBody
    public JsonResult getScenesByMovie(@PathVariable Integer id) {
        List<Scene> scenes = sceneService.selectByMovieId(id);
        JsonResult result;
        if (scenes != null) {
            result = JsonResult.success("查询电影场次成功", scenes);
        } else {
            result = JsonResult.error(200, "该电影没有场次");
        }
        return result;
    }


    /**
     * 创建场次
     *
     * @param scene 插入的场次的信息，scene的movieName允许填错,scene.seatNum默认为166
     * @return 插入的看结果, 插入成功返回1
     */
    @PostMapping("/scense")
    @ResponseBody
    public JsonResult createScene(@RequestBody Scene scene) {
        int i = sceneService.insertOneScene(scene);
        JsonResult result;
        if (i == 1) {
            result = JsonResult.success("创建场次成功", null);
        } else {
            result = JsonResult.error(400, "创建失败");
        }
        return result;
    }


    /**
     * 修改场次信息
     *
     * @param id    修改场次的id
     * @param scene 修改后的场次的信息，scene.seatNum默认为166
     * @return 返回JsonResult对象，HTTP状态码：200 -- 成功，400 -- 修改失败，HTTP状态码：404 -- 资源未找到
     */
    @PutMapping("/scenes/{id}")
    @ResponseBody
    public JsonResult updateScene(@PathVariable Integer id, @RequestBody Scene scene) {
        scene.setId(id);
        Scene find = sceneService.getOneScene(id);
        if (find == null) {
            return JsonResult.error(404, "没有找到该场次");
        }
        if (sceneService.updateOneScene(scene) >= 1) {
            return JsonResult.success("修改成功", null);
        }
        return JsonResult.error(400, "修改场次信息失败");
    }


    /**
     * 通过id删除场次信息
     *
     * @param id 删除的场次的id
     * @return 返回JsonResult对象，HTTP状态码：200 -- 成功，400 -- 修改失败，HTTP状态码：404 -- 资源未找到
     */
    @DeleteMapping("/scenes/{id}")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id) {
        Scene find = sceneService.getOneScene(id);
        JsonResult result;
        if (find == null) {
            result = JsonResult.error(404, "没有找到该场次");
        } else {
            int i = sceneService.deleteOneScene(id);
            if (i == 1) {
                result = JsonResult.success("删除场次成功", null);
            } else {
                result = JsonResult.error(400, "删除场次失败");
            }
        }
        return result;
    }
}
