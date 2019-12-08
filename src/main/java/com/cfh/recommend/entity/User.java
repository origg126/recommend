package com.cfh.recommend.entity;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 14:43
 */
public class User implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String username;
    private String password;
    @Transient
    private List<UserLike> userLikes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserLike> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<UserLike> userLikes) {
        this.userLikes = userLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        User user = (User) o;

        if (!id.equals(user.id)){
            return false;
        }
        if (!username.equals(user.username)){
            return false;
        }
        return userLikes.equals(user.userLikes);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + userLikes.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userLikes=" + userLikes +
                '}';
    }
}
