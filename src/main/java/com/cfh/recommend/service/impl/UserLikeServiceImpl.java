package com.cfh.recommend.service.impl;

import com.cfh.recommend.entity.User;
import com.cfh.recommend.entity.UserLike;
import com.cfh.recommend.entity.Video;
import com.cfh.recommend.service.UserLikeService;
import com.cfh.recommend.service.UserService;
import com.cfh.recommend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author origg
 * @version 1.0
 * @date 2019/12/8 16:12
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = RuntimeException.class)
public class UserLikeServiceImpl implements UserLikeService {
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

    @Override
    public List<Video> findUserLike(String userId) {
        //此处可以根据标签挑选用户
        List<User> users = userService.queryAll();
        //此处可以用户标签挑选热门视频
        List<Video> videos = videoService.queryAll();
        //目标用户
        User targetUser = userService.queryOneAndLike(userId);
        //将目标用户从用户集合中删除
        users.remove(targetUser);
        //目标用户平均评评分
        Double targetUserAvg = getAvg(targetUser);
        //询找邻居用户，也就是目标用户没看过，但是哪些用户看过了，并有评分，那么这些用户就是邻居用户
        //并根据邻居用户对每个没有看过的电影进行预估评分
        Map<Video, Double> mark = new HashMap<>(16);
        for (Video video : videos) {

            List<UserLike> targetUserLikes = targetUser.getUserLikes();
            //设置一个标记
            int flag = 0;
            for (UserLike targetUserLike : targetUserLikes) {
                //如果这个电影看过了，将标记值变为1，并直接跳出循环
                if (video.getId().equals(targetUserLike.getVideoId())) {
                    flag = 1;
                    break;
                }
            }
            //如果标记值为0，也就是说这个电影没有看过
            if(flag == 0){
                //如果这个电影目标用户没有看过，那么他就存在邻居用户,并保持顺序，方便匹配
                Map<User,Double> neighborUser = new HashMap<>(16);
                for (User user : users) {
                    List<UserLike> userLikes = user.getUserLikes();
                    for (UserLike userLike : userLikes) {
                        //如果这个用户看过这个电影，就将这个用户当成邻居用户
                        if (video.getId().equals(userLike.getVideoId())) {
                            //计算这个邻居用户与目标用户的相似度，使用皮尔逊相关系数计算公式
                            Double sm = compare(targetUser, user, targetUserAvg);
                            neighborUser.put(user, sm);
                        }
                    }
                }

                //根据这个电影对应的邻居集合，预估目标用户对该电影的评分
                Double videoMark = getMark(targetUserAvg, neighborUser,video.getId());
                mark.put(video, videoMark);
            }
        }
        System.out.println("=======================Mark========================");
        mark.forEach((k,v)->{
            System.out.println("video:" + k.getName() + "----mark:" + v);
        });
        return null;
    }

    /**
     * 根据邻居用户，对目标用户进行预测评分
     * @param targetUserAvg
     * @param neighborUser
     * @param videoId
     * @return
     */
    private Double getMark(Double targetUserAvg,Map<User,Double> neighborUser,String videoId) {
        Double sumUp = 0D;
        Double sumDown = 0D;
        for (User user : neighborUser.keySet()) {
            for (UserLike userLike : user.getUserLikes()) {
                if (videoId.equals(userLike.getVideoId())) {
                    //获取该用户与目标用户的相似度
                    Double sm = neighborUser.get(user);
                    //获取该用户的怕平均评分
                    Double avg = getAvg(user);
                    sumUp += sm * (userLike.getCount() - avg);
                    break;
                }
            }
            sumDown += Math.abs(neighborUser.get(user));
        }
        return targetUserAvg + (sumUp / sumDown);
    }

    /**
     * 使用皮尔逊相关系数公式，计算两个用户的相似度
     * @param targetUser
     * @param user
     * @param targetUserAvg
     * @return
     */
    private Double compare(User targetUser, User user, Double targetUserAvg) {
        //创建目标用户的评分集合
        List<Double> targetUserList = new ArrayList<>();
        //创建邻居用户的评分集合
        List<Double> userList = new ArrayList<>();
        //获取目标用户爱好集合
        List<UserLike> targetUserLikes = targetUser.getUserLikes();
        //获取邻居用户爱好集合
        List<UserLike> userLikes = user.getUserLikes();

        for (UserLike targetUserLike : targetUserLikes) {
            for (UserLike userLike : userLikes) {
                //如果存在两个用户都喜欢的影片
                if (targetUserLike.getVideoId().equals(userLike.getVideoId())) {
                    targetUserList.add(targetUserLike.getCount());
                    userList.add(userLike.getCount());
                }
            }
        }
        //获取邻居用户平均评分
        Double userAvg = getAvg(user);
        Double similarity = getSimilarity(targetUserList, userList, targetUserAvg, userAvg);
        return similarity;
    }

    private Double getAvg(User user) {
        Double sum = 0D;
        for (UserLike like : user.getUserLikes()) {
            sum += like.getCount();
        }
        return sum / user.getUserLikes().size();
    }

    private Double getSimilarity(List<Double> targetList, List<Double> list,Double targetUserAvg,Double userAvg) {
        Double sumUp = 0D;
        for (int i = 0; i < targetList.size(); i++) {
            sumUp += (targetList.get(i) - targetUserAvg) * (list.get(i) - userAvg);
        }
        Double sumDownTarget = 0D;
        for (Double count : targetList) {
            sumDownTarget += Math.pow((count - targetUserAvg), 2);
        }
        Double sumDownUser = 0D;
        for (Double count : list) {
            sumDownUser += Math.pow((count - userAvg), 2);
        }
        return sumUp / (Math.sqrt(sumDownTarget) * Math.sqrt(sumDownUser));
    }
}
