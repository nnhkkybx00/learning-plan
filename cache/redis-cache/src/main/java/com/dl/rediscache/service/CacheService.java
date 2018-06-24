package com.dl.rediscache.service;

import com.alibaba.fastjson.JSON;
import com.dl.rediscache.dao.OrderDao;
import com.dl.rediscache.entity.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class CacheService {
    private static Logger logger = LogManager.getLogger(CacheService.class);


    @Autowired
    private OrderDao orderDao;

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String cache_key = "dl";

    @SuppressWarnings("rawtypes")
    public /** synchronized *这里加锁粒度太大*/ List<Product> query(){
        //TODO
        //1.查缓存
        ValueOperations ops = redisTemplate.opsForValue();
        String json = String.valueOf(ops.get(cache_key));//json字符串,跨平台，比返回实体类好

        //2.判断，如果有数据，返回数据，如果没有
        if(!StringUtils.isEmpty(json) && !json.equalsIgnoreCase("null")){
            //返回缓存中的数据
            logger.info("================================query for cache===============================");
            return JSON.parseArray(json,Product.class);
        }

        // double check lock
        synchronized (this) {
            json = String.valueOf(ops.get(cache_key));
            if(!StringUtils.isEmpty(json) && !json.equalsIgnoreCase("null")){
                //返回缓存中的数据
                logger.info("================================query for cache===============================");
                return JSON.parseArray(json,Product.class);
            }
            //3.查询数据库，并且返回数据，同时更新缓存
            List<Product> list = orderDao.getAll();//这里会有延迟，当并发高时，还没有来得及把缓存，下一个线程就又走到这里
            ops.set(cache_key, JSON.toJSON(list),10, TimeUnit.MINUTES);
            return list;
        }

    }

}
