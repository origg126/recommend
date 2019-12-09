package com.cfh.recommend.controller;

import com.cfh.recommend.entity.User;
import com.cfh.recommend.entity.Video;
import com.cfh.recommend.service.UserLikeService;
import com.cfh.recommend.service.UserService;
import com.cfh.recommend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/9 9:07
 */
@RestController
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLikeService userLikeService;

    @RequestMapping("showAll")
    public Map<String, Object> showAll(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(4);
        try {
            User user = (User) request.getSession().getAttribute("user");
            List<Video> videos = videoService.queryAll();
            User user1 = userService.queryOneAndLike(user.getId());
            Map<Video, Double> userLike = userLikeService.findUserLike(user.getId());
            map.put("status", 200);
            map.put("videos", videos);
            map.put("user", user1);
            map.put("userLike", userLike);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", -200);
            map.put("message", "读取失败");
        }
        return map;
    }
}
