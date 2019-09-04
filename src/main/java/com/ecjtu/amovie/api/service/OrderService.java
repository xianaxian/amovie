package com.ecjtu.amovie.api.service;

import com.ecjtu.amovie.api.entity.Order;
import com.ecjtu.amovie.api.entity.Scene;
import com.ecjtu.amovie.api.repository.OrderRepository;
import com.ecjtu.amovie.api.repository.SceneRepository;
import com.ecjtu.amovie.form.OrderResult;
import com.ecjtu.amovie.utils.Json;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xianaxian  2019-09-02 19:37
 */
@Service
public class OrderService {
    private final SceneRepository sceneRepository;
    private final OrderRepository orderRepository;

    public OrderService(SceneRepository sceneRepository, OrderRepository orderRepository) {
        this.sceneRepository = sceneRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public int saveOrder(Order order) {
        order.setCreateTime(new Date());
        orderRepository.saveOne(order);
        Scene scene = sceneRepository.selectOne(order.getSceneId());
        String bookedSeat = scene.getBookedSeat();
        if (StringUtils.isEmpty(bookedSeat)){
            bookedSeat="[]";
        }
        List<String> booked = Json.parseArray(order.getBookedSeated(), String.class);
        HashSet<String> hashSet = Json.parseObject(bookedSeat, HashSet.class);
        hashSet.addAll(booked);
        String temp = Json.toJson(hashSet);
        scene.setBookedSeat(temp);
        return sceneRepository.updateOne(scene);
    }

    public OrderResult selectInfo(int id) {
        OrderResult orderResult = orderRepository.selectInfo(id);
        String seats = orderResult.getBookedSeated();
        seats= StringUtils.isEmpty(seats) ? "[]" : seats;
        List<String> strings = Json.parseArray(seats, String.class);
        orderResult.setBookedSeated(String.join(",", strings));
        return orderResult;
    }
    public List<OrderResult> selectInfoByUser(int id) {
        List<OrderResult> orderResults = orderRepository.selectInfoByUser(id);
        for (OrderResult orderResult : orderResults) {
            String seats = orderResult.getBookedSeated();
            seats= StringUtils.isEmpty(seats) ? "[]" : seats;
            List<String> strings = Json.parseArray(seats, String.class);
            orderResult.setBookedSeated(String.join(",", strings));
        }
        return orderResults;
    }
}
