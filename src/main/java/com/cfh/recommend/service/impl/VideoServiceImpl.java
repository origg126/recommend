package com.cfh.recommend.service.impl;

import com.cfh.recommend.dao.VideoDao;
import com.cfh.recommend.entity.Video;
import com.cfh.recommend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 16:05
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = RuntimeException.class)
    public List<Video> queryAll() {
        return videoDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = RuntimeException.class)
    public Video queryOne(String videoId) {
        return videoDao.selectByPrimaryKey(videoId);
    }
}
