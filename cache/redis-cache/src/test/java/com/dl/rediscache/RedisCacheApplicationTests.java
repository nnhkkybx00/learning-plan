package com.dl.rediscache;

import com.alibaba.fastjson.JSONArray;
import com.dl.rediscache.entity.Product;
import com.dl.rediscache.service.CacheService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheApplicationTests {
    private static Logger logger = LogManager.getLogger(RedisCacheApplicationTests.class.getName());
    private static final int THREAD_NUM = 10;
    private static final String cache_key = "dl";

    //倒计时
    private CountDownLatch cdl = new CountDownLatch(THREAD_NUM);

    @Autowired
    private CacheService cacheService;
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    @Before
    public void init(){
        redisTemplate.delete(cache_key);
    }

    @Test
    public void test() throws InterruptedException{
        for (int i = 0;i < THREAD_NUM; i++){
            //模拟10个并发线程
            new Thread(new QueryTask()).start();
            cdl.countDown();
        }
        Thread.currentThread().sleep(5000);
    }

    private class QueryTask implements Runnable{
        @Override
        public void run() {
            try{
                cdl.await();//等于0的时候一起运行
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //
//            List<Product> list = cacheService.query();
            List<Product> list = cacheService.queryByTempleate();
            logger.info(list.toString());
        }
    }

    @Test
    public void query() {
        logger.info("===========================query start=================================");

        List<Product> list = cacheService.query();

        logger.info(list.toString());
        List<Product> list2 = cacheService.query();
        logger.info(list2.toString());
        logger.info("===========================query end=================================");
    }

}
