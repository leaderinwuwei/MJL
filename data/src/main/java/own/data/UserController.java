package own.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("redisPingPang")
    private DefaultRedisScript<String> releaseScript;

    @RequestMapping("/getUser")
    @Cacheable(value = "user-key")
    public User getUser() {
        User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }


    @RequestMapping("/uid")
    String uid(HttpSession session) {
        redisTemplate.opsForValue().set("test4", "value4");
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

    @RequestMapping("/ping")
    String ping() {
        System.out.println("11111");
        System.out.println("11111");
        System.out.println("11111");

        return (String) redisTemplate.execute(releaseScript, new ArrayList(), new String[]{});

    }
}