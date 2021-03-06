package com.cfh.recommend.service;

import com.cfh.recommend.entity.Video;

import java.util.Map;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 16:11
 */
public interface UserLikeService {

    /**
     * 给某个用户推荐影片
     * @param userId
     * @return
     */
    Map<Video, Double> findUserLike(String userId);
}
