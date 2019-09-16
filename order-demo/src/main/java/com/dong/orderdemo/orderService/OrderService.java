package com.dong.orderdemo.orderService;

import com.dong.orderdemo.orderService.pool.CacheCommand;
import com.dong.orderdemo.orderService.pool.OrderCommand;
import com.dong.orderdemo.orderService.pool.UserCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    RestTemplate restTemplate;



    @HystrixCommand(fallbackMethod = "userFallback")
    public String getUser(int id) {
        // 获取用户信息？？？
        String url = "http://dong-user/user/{id}";

        String info = restTemplate.getForObject(url, String.class, id);
        return info;
    }

//继承类的方式
    public String getUser2(Integer id){

//        Long cachekey = 9999L + id;
        CacheCommand cacheCommand = new CacheCommand(id,restTemplate,9999l);
        String val = cacheCommand.execute();
        cacheCommand.clearRequestCache();
        return val;
    }

//继承注解的方式
    @CacheResult
    @HystrixCommand(commandKey = "cache-user")
    public String getUser3(Integer id,@CacheKey Long cacheKey){

    String url = "http://user-dong/user/{id}";
    String info = restTemplate.getForObject(url,String.class,id);
    return info;

    }
    //注解清空
    @CacheRemove(commandKey = "cache-user")
    @HystrixCommand(commandKey = "cache-user")
    public void clearRequestCache(){

    }


    //添加服务降级方法
    public String userFallback(int id){

        return "error usefallback";
    }


    //测试依赖隔离
    public String testPool(){

        UserCommand userCommand = new UserCommand("用户");

        OrderCommand orderCommand1 = new OrderCommand("订单");
        OrderCommand orderCommand2 = new OrderCommand("订单2");


        //同步调用
        String value1 = userCommand.execute();
        String value2 = orderCommand1.execute();
        String value3 = orderCommand2.execute();
        return "  value1  "+value1+"  value2  "+value2+"  value3  "+value3;
    }


}
