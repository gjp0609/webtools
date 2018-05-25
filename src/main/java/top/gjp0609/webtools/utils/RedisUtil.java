package top.gjp0609.webtools.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {

    private final StringRedisTemplate template;

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class.getSimpleName());

    @Autowired
    public RedisUtil(StringRedisTemplate template) {
        this.template = template;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public boolean expire(String key, long time) {

        try {
            if (time > 0) {
                template.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public StringRedisTemplate getTemplate() {
        return template;
    }

    public boolean hasKey(String key) {
        if (key == null || StringUtils.isBlank(key)) {
            LOGGER.debug("key is null or blank");
            return false;
        }
        return template.hasKey(key);
    }


    public String getStr(String key) {
        if (key == null || StringUtils.isBlank(key)) {
            LOGGER.debug("key is null or blank");
            return null;
        }
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }


    public boolean setKeyVal(String key, String value) {
//        RedisSerializer<>
//        template.setStringSerializer();
        return true;
    }

    public boolean setKeyVal(String key, String value, long time) {
        return true;
    }


}
