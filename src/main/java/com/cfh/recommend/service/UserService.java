package com.cfh.recommend.service;

import com.cfh.recommend.entity.User;

import java.util.List;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 15:55
 */
public interface UserService {

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    User queryOne(String username,String password);


    /**
     * 根据id查询一个用户，并查询该用户的喜好
     * @param userId
     * @return
     */
    User queryOneAndLike(String userId);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAll();
}
