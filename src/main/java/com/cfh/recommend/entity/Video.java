package com.cfh.recommend.entity;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 14:46
 */

public class Video implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String name;

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Video video = (Video) o;

        if (!id.equals(video.id)) {
            return false;
        }
        return name.equals(video.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
