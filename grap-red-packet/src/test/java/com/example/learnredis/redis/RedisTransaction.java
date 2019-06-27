package com.example.learnredis.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTransaction {
    private Logger logger= LoggerFactory.getLogger(RedisTransaction.class);
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public  void operateTransactionWithRedisTemplate(){
        //默认不支持事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        redisTemplate.opsForValue().set("user1",new User(1,"zs",1));
        redisTemplate.opsForValue().set("user2",new User(2,"ls",2));
        redisTemplate.exec();
    }

    @Test
    public void oprateTransactionWithSessionCallback(){
        SessionCallback<List<Object>> callback =new SessionCallback<List<Object>>() {
            @Override
            /**
             * 泛型方法泛型参数不必指定，调用时指定
             */
            public    List<Object> execute(RedisOperations  operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("user3",new User(3,"ww",3));
                operations.opsForValue().set("user4",new User(4,"zl",4));
                return operations.exec();
            }
        };

        List<Object> list=redisTemplate.execute(callback);
        for(Object object:list){
            System.out.println(object);
        }
    }

}
