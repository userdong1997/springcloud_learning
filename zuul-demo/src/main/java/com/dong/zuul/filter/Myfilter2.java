package com.dong.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class Myfilter2 extends ZuulFilter {


    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    //优先级，数字越大，优先级越低
    @Override
    public int filterOrder() {
        return 2;
    }

    //是否执行该过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(" myfilter2 ");

        return null;
    }
}
