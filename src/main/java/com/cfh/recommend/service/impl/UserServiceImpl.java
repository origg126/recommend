package com.cfh.recommend.service.impl;

import com.cfh.recommend.dao.UserDao;
import com.cfh.recommend.dao.UserLikeDao;
import com.cfh.recommend.entity.User;
import com.cfh.recommend.entity.UserLike;
import com.cfh.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 16:02
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLikeDao userLikeDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public User queryOne(String username,String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userDao.selectOne(user);
    }

    @Override
    public UserLike queryOneUserLike(String userId, String videoId) {
        UserLike userLike = new UserLike();
        userLike.setUserId(userId);
        userLike.setVideoId(videoId);
        return userLikeDao.selectOne(userLike);
    }

    @Override
    public void updateUserLike(UserLike userLike) {
        Example example = new Example(UserLike.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userLike.getUserId()).andEqualTo("videoId", userLike.getVideoId());
        userLikeDao.updateByExampleSelective(userLike, example);
    }

    @Override
    public void insertUserLike(UserLike userLike) {
        userLikeDao.insert(userLike);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public User queryOneAndLike(String userId) {
        return userDao.queryUserLike(userId);
    }

    @Override
    public List<User> queryAll() {
        return userDao.queryAllUserLike();
    }
}
