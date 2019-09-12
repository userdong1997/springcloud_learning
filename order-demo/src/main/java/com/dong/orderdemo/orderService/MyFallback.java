package com.dong.orderdemo.orderService;

import com.dong.orderdemo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class MyFallback implements FeignService{


    @Override
    public String getUser(int id) {
        return "getUser game over";
    }

    @Override
    public String getUser2(User user) {
        return "getUser2 game over";
    }
}
