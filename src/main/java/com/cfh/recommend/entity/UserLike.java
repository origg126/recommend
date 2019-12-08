package com.cfh.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "userlike")
public class UserLike implements Serializable {
    @Column(name = "userId")
    private String userId;
    @Column(name = "videoId")
    private String videoId;
    private Double count;
    private Video video;
}
