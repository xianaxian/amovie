package com.ecjtu.amovie.contrller;

import com.ecjtu.amovie.api.entity.User;
import com.ecjtu.amovie.api.service.OrderService;
import com.ecjtu.amovie.api.service.ScoreService;
import com.ecjtu.amovie.api.service.UserService;
import com.ecjtu.amovie.api.service.WatchListService;
import com.ecjtu.amovie.form.MovieResult;
import com.ecjtu.amovie.form.OrderResult;
import com.ecjtu.amovie.form.RateForm;
import com.ecjtu.amovie.form.RegisterUserForm;
import com.ecjtu.amovie.utils.MD5Utils;
import com.ecjtu.amovie.utils.result.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author xianaixan
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final WatchListService watchListService;
    private final ScoreService scoreService;
    private final OrderService orderService;

    public UserController(UserService userService, WatchListService watchListService, ScoreService scoreService, OrderService orderService) {
        this.userService = userService;
        this.watchListService = watchListService;
        this.scoreService = scoreService;
        this.orderService = orderService;
    }


    @ResponseBody
    @RequestMapping("/register")
    public JsonResult<String> register(RegisterUserForm rUser) {
        User user = new User();
        BeanUtils.copyProperties(rUser, user);
        int i = userService.register(user);
        if (i == 1) {
            return JsonResult.success("注册成功", "/");
        } else {
            return JsonResult.error(400, "注册失败");
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public JsonResult<String> login(String email, String password, HttpSession session) {
        User login = userService.findOneByEmail(email);
        if (login == null) {
            return JsonResult.error(233, "登陆失败，错误的用户名或密码");
        }
        if (login.getPassword().equals(MD5Utils.md5(password, login.getSalt()))) {
            //登陆成功
            session.setAttribute("user", login);
            return JsonResult.success("登陆成功", null);
        } else {
            return JsonResult.error(233, "登陆失败，错误的用户名或密码");
            //登陆失败
        }
    }


    @GetMapping("/watchlist")
    public ModelAndView watchlist(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                  HttpSession session) {
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        PageInfo<MovieResult> watchlist = watchListService.watchlist(pageNum, pageSize, user.getId());
        mav.addObject("watchlist", watchlist);
        mav.setViewName("watchlist");
        return mav;
    }

    @PostMapping("/watchlist")
    @ResponseBody
    public JsonResult addWatchlist(Integer movieId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return JsonResult.error(401, "你没有登陆");
        }
        int i = watchListService.insertWatchlist(movieId, user.getId());
        if (i == 1) {
            return JsonResult.success("添加到播放列表成功", null);
        }
        return JsonResult.error(404, "添加失败");
    }
    @PostMapping("/delWatchlist")
    @ResponseBody
    public JsonResult delWatchlist(Integer movieId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return JsonResult.error(401, "你没有登陆");
        }
        int i = watchListService.deleteWatchlist(movieId, user.getId());
        if (i == 1) {
            return JsonResult.success("移除出播放列表成功", null);
        }
        return JsonResult.error(404, "移除失败");
    }

    @GetMapping("/ticket")
    public ModelAndView ticket( HttpSession session){
        ModelAndView mav=new ModelAndView();
        User user=(User)session.getAttribute("user");
        List<OrderResult> orderResults = orderService.selectInfoByUser(user.getId());
        mav.addObject("tickets", orderResults);
        mav.setViewName("ticket");
        return mav;
    }
    @PostMapping("/rate")
    @ResponseBody
    public JsonResult rate(@Valid @RequestBody RateForm rate, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return JsonResult.error(401, "你没有登陆");
        }
        int i = scoreService.insertScore(rate.getMovieId(), user.getId(),rate.getScore() );
        if (i == 1) {
            return JsonResult.success("评分成功", null);
        }
        return JsonResult.error(404, "评分失败");
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
