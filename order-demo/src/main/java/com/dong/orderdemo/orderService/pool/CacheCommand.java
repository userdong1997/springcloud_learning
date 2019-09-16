package com.dong.orderdemo.orderService.pool;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class CacheCommand extends HystrixCommand<String> {

    private Long cachekey;
    private RestTemplate restTemplate;
    private Integer id;

    public CacheCommand(Integer id, RestTemplate restTemplate,Long cachekey) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("cache-key"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("cache-pool"))

        );
        this.id = id;
        this.cachekey = cachekey;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {

        System.out.print("没有缓存的查询");
        String url = "http://dong-user/user/{id}";

        String info = restTemplate.getForObject(url, String.class, id);
        return info;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(cachekey);
    }

    public void clearRequestCache(){
        HystrixRequestCache.getInstance(
                HystrixCommandKey.Factory.asKey("cache-pool"),
                HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(cachekey));
    }
}
