package com.lyl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyl.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        // redisTemplate 操作不同的数据类型
        // opsForValue() 操作字符串  类似String
        // opsForList()  操作list    类似list
        // ......
        redisTemplate.opsForValue().set("name", "liyl");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    void testUser() throws JsonProcessingException {

        // 真实开发一般用json来传递对象
        User user = new User("liyl", 15);
        // String stringUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
