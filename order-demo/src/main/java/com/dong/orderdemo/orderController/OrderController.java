package com.dong.orderdemo.orderController;

import com.dong.orderdemo.orderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/order")
    public String addOrder( String name, int id) {
        // 调用用户，查询用户信息，
        String result = orderService.getUser(id);

        return "商品名称"+name +"， 生成订单：" + result;
    }
}
