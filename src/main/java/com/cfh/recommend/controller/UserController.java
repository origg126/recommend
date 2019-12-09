package com.cfh.recommend.controller;

import com.cfh.recommend.entity.User;
import com.cfh.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/9 8:59
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Map<String, Object> login(String username, String password, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(2);
        User user = userService.queryOne(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            map.put("status", 200);
            map.put("user", user);
        }else{
            map.put("status", -200);
            map.put("message", "用户名或密码错误");
        }
        return map;
    }
}
