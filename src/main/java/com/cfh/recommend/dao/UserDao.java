package com.cfh.recommend.dao;

import com.cfh.recommend.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 15:06
 */
public interface UserDao extends Mapper<User> {

    /**
     * 根据id查询该用户的喜好
     * @param userId
     * @return
     */
    User queryUserLike(String userId);

    /**
     * 查询所有用户，包括他们的爱好
     * @return
     */
    List<User> queryAllUserLike();
}
