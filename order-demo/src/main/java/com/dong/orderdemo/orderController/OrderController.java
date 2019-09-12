package com.dong.orderdemo.orderController;

import com.dong.orderdemo.model.User;
import com.dong.orderdemo.orderService.FeignService;
import com.dong.orderdemo.orderService.OrderService;
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


}
