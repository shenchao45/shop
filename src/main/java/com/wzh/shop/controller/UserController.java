package com.wzh.shop.controller;

import com.wzh.shop.entity.User;
import com.wzh.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    @ResponseBody
    public String add(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userRepository.save(user);
        return "susccess";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        User u = userRepository.findByUsernameAndPassword(user.getUsername(), password);
        if (u == null) {
            return "fail";
        }else{
            request.getSession().setAttribute("user",u);
            response.sendRedirect("/templates/item_list.html");
            return "success";
        }
    }

    @GetMapping("/logout")
    public String login(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "success";
    }
}
