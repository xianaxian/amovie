package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.Order;
import com.ecjtu.amovie.api.entity.Scene;
import com.ecjtu.amovie.api.entity.User;
import com.ecjtu.amovie.api.service.OrderService;
import com.ecjtu.amovie.api.service.SceneService;
import com.ecjtu.amovie.form.OrderFrom;
import com.ecjtu.amovie.form.OrderResult;
import com.ecjtu.amovie.utils.Json;
import com.ecjtu.amovie.utils.result.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author xianaxian  2019-09-01 20:40
 */
@Controller
public class BookController {
    private final SceneService sceneService;
    private final OrderService orderService;

    public BookController(SceneService sceneService, OrderService orderService) {
        this.sceneService = sceneService;
        this.orderService = orderService;
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
    @GetMapping("/book3")
    public ModelAndView book3(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("book3-buy");
        return mav;
    }

    @PostMapping("/book3")
    @ResponseBody
    public JsonResult addTicket(@RequestBody OrderFrom order, HttpSession session) {
        if (order==null) {
            return JsonResult.error(403, "请求有误");
        }
        order.setTotalPrice(order.getPrice()*order.getTicketNum());
        session.setAttribute("order", order);
        return JsonResult.success("预订成功","/book3");
    }

    @GetMapping("/book-final")
    public ModelAndView bookFinal(HttpSession session){
        User user =(User)  session.getAttribute("user");
        OrderFrom orderFrom = (OrderFrom) session.getAttribute("order");
        if (user==null || orderFrom ==null){
            throw  new RuntimeException("您没有登陆或者没有购票");
        }
        Order order=new Order(null, 1, user.getId(), new Date(), orderFrom.getSceneId(), orderFrom.getTicketNum(), orderFrom.getTotalPrice(), Json.toJson(orderFrom.getBookedSeated()));
        orderService.saveOrder(order);
        OrderResult orderResult = orderService.selectInfo(order.getId());
        ModelAndView mav=new ModelAndView();
        mav.setViewName("book-final");
        mav.addObject("order", orderResult);
        return mav;
    }
}
