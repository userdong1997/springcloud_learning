package com.dong.userdemo.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${server.port}")
    int port;


    @RequestMapping("/user/{id}")
    public String getUser(@PathVariable("id") int id) {

        if (id == 1) {
            return "id==1 +"+port;
        } else if (id == 2) {
            return "id==2 +"+port;
        } else {
            return "其他 +"+port;
        }
    }
}
