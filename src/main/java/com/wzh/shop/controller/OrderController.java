package com.wzh.shop.controller;

import com.wzh.shop.entity.OrderItem;
import com.wzh.shop.entity.User;
import com.wzh.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/list")
    @ResponseBody
    public List<OrderItem> list(){
        return orderRepository.findAll();
    }

    @PostMapping("/buy")
    @ResponseBody
    public String buy(HttpServletRequest request,OrderItem orderItem){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "fail";
        }
        orderItem.setCreateTime(new Date());
        orderItem.setUserId(user.getId());
        orderRepository.save(orderItem);
        return "success";
    }

}
