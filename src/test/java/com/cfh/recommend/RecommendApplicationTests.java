package com.cfh.recommend;

import com.cfh.recommend.service.UserLikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecommendApplicationTests {
    @Autowired
    private UserLikeService userLikeService;

    @Test
    void smTest() {
        userLikeService.findUserLike("124");
    }

}
