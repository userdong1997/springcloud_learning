package com.dong.orderdemo;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class OrderApp {

    public static void main(String[] args) {

        SpringApplication.run(OrderApp.class, args);

    }
//封装的是http请求
    @Bean
    @LoadBalanced   //负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
