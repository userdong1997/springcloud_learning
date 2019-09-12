package com.dong.orderdemo.orderService;

import com.dong.orderdemo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "dong-user",fallback = MyFallback.class)
public interface FeignService {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    String getUser(@PathVariable("id") int id);

    @RequestMapping(value = "user2",method = RequestMethod.POST)
    String getUser2(User user);
}
