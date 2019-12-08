package com.cfh.recommend.service.impl;

import com.cfh.recommend.dao.UserDao;
import com.cfh.recommend.entity.User;
import com.cfh.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public User queryOne(String userId) {
        return userDao.selectByPrimaryKey(userId);
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
