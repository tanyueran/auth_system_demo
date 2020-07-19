import com.github.tanyueran.auth_system_springboot.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate myRedisTemplate;

    @org.junit.Test
    public void run1() {
        myRedisTemplate.opsForValue().set("key", "value");
        String key = (String) myRedisTemplate.opsForValue().get("key");
        System.out.println(key);

    }
}
