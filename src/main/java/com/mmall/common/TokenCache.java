package com.mmall.common;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class TokenCache {

    public static final String TOKEN_PREFIX="token_";

    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    // 最多10000个缓存，超过后会使用最近最久未使用策略（LRU）来清除缓存 缓存有效期12小时
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(30, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
        // 默认的数据加载实现，当调用get取值时，如果key没有对应的值，就调用该方法进行加载
        @Override
        public String load(String key) throws Exception {
            return "null";
        }
    });
    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value=null;
        try{
            value=localCache.get(key);
            if("null".equals(value)){
                return null;
            }
            return value;
        }catch (Exception e){
            logger.error("localCache get error",e);
        }
        return null;
    }
}
