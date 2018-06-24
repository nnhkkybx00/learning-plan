package com.dl.rediscache.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dl.rediscache.dao.OrderDao;
import com.dl.rediscache.entity.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
@Component
public class CacheTemplateService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderDao orderDao;
    private static Logger logger = LogManager.getLogger(CacheTemplateService.class);

    /**
     * 避免缓存穿透的模板
     * 读取缓存的模板操作
     * @param key 缓存的key
     * @param expire 缓存失效时间
     * @param unit 失效时间单位
     * @param clazz 缓存的类型
     * @param cacheLoadable 如果缓存失效，怎么获取
     * @param <T>
     * @return
     */
    public <T> T findCache(String key, long expire, TimeUnit unit,
                           TypeReference<T> clazz, CacheLoadable<T> cacheLoadable){
        ValueOperations operations = redisTemplate.opsForValue();
        String json = String.valueOf(operations.get(key));//json字符串,跨平台，比返回实体类好

        //2.判断，如果有数据，返回数据，如果没有
        if(!StringUtils.isEmpty(json) && !json.equalsIgnoreCase("null")){
            //返回缓存中的数据
            logger.info("================================query for cache===============================");
            return JSON.parseObject(json,clazz);
        }else {
            synchronized (this) {
                json = String.valueOf(operations.get(key));
                if(!StringUtils.isEmpty(json) && !json.equalsIgnoreCase("null")){
                    //返回缓存中的数据
                    logger.info("================================query for cache===============================");
                    return JSON.parseObject(json,clazz);
                }
                //核心业务
               T result = cacheLoadable.load();
                operations.set(key, JSON.toJSON(result),expire, unit);
                return result;
            }
        }
    }
}
