package com.cfh.recommend.service;

import com.cfh.recommend.entity.Video;

import java.util.List;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 15:57
 */
public interface VideoService {

    /**
     * 查询所有影片
     * @return
     */
    List<Video> queryAll();


    /**
     * 查询一个影片
     * @param videoId
     * @return
     */
    Video queryOne(String videoId);
}
