package com.dong.userdemo.Controller;

import com.dong.userdemo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/user2",method = RequestMethod.POST)
    public String getUser2(@RequestBody User user) {

        return "ok "+user.getName();
    }

}
