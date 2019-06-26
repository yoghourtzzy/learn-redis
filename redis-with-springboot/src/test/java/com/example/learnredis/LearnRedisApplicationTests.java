package com.example.learnredis;

import com.example.learnredis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnRedisApplicationTests {
    private Logger logger= LoggerFactory.getLogger(LearnRedisApplicationTests.class);
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void contextLoads() {
        String key="user:1";
        User user=new User(1,"zs",18);
        redisTemplate.opsForValue().set(key,user);
        User user1 = (User) redisTemplate.opsForValue().get(key);
        logger.info("uesr: "+user.toString());
    }

}
