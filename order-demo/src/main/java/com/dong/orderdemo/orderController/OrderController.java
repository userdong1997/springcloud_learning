package com.dong.orderdemo.orderController;

import com.dong.orderdemo.model.User;
import com.dong.orderdemo.orderService.FeignService;
import com.dong.orderdemo.orderService.OrderService;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Resource
    FeignService feignService;


    @RequestMapping("/order")
    public String addOrder( String name, int id) {
        // 调用用户，查询用户信息，
        String result = orderService.getUser(id);

        return "商品名称"+name +"， 生成订单：" + result;
    }

    @RequestMapping(value = "/order2",method = RequestMethod.POST)
    public String addOrder2(@RequestBody User user) {
        // 调用用户，查询用户信息，

        String result = feignService.getUser2(user);

        return " 生成订单：" + result;
    }

    @RequestMapping("/pool")
    public String pool( ) {

       return orderService.testPool();
    }

    @RequestMapping("/cache")
    public String cacheUser( ) {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();



        //查询用户
        String result1 = orderService.getUser2(1);
        String result2 = orderService.getUser2(2);
        String result3 = orderService.getUser2(3);

        context.close();


        return " result1 "+ result1 + "\n result2 "+ result2+"\n result3 "+ result3;
    }

    //注解
    @RequestMapping("/cache2")
    public String cacheUser2( ) {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();



        //查询用户
        String result1 = orderService.getUser3(1,1L);
        String result2 = orderService.getUser3(2,1l);
        String result3 = orderService.getUser3(3,1l);

        context.close();


        return " result1 "+ result1 + "\n result2 "+ result2+"\n result3 "+ result3;
    }




}
