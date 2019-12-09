package com.cfh.recommend.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 14:54
 */
@Table(name = "userlike")
public class UserLike implements Serializable {
    @Column(name = "userId")
    private String userId;
    @Column(name = "videoId")
    private String videoId;
    private Double count;
    @Transient
    private Video video;

    @Override
    public String toString() {
        return "UserLike{" +
                "userId='" + userId + '\'' +
                ", videoId='" + videoId + '\'' +
                ", count=" + count +
                ", video=" + video +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserLike userLike = (UserLike) o;

        if (!videoId.equals(userLike.videoId)){
            return false;
        }
        if (!count.equals(userLike.count)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + videoId.hashCode();
        result = 31 * result + count.hashCode();
        return result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
