package com.cfh.recommend.controller;

import com.cfh.recommend.entity.User;
import com.cfh.recommend.entity.UserLike;
import com.cfh.recommend.service.UserLikeService;
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
    @Autowired
    private UserLikeService userLikeService;

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

    @RequestMapping("changeUserLike")
    public Map<String, Object> changeUserLike(String userId,String videoId,Double score) {
        Map<String, Object> map = new HashMap<>(2);
        try {
            UserLike userLike = userService.queryOneUserLike(userId, videoId);
            if (userLike == null) {
                userLike = new UserLike();
                userLike.setUserId(userId);
                userLike.setVideoId(videoId);
                userLike.setCount(score);
                userService.insertUserLike(userLike);
            }else{
                userLike.setCount(score);
                userService.updateUserLike(userLike);
            }
            map.put("status", 200);
            map.put("message", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", -200);
            map.put("message", "修改失败");
        }
        return map;
    }
}
