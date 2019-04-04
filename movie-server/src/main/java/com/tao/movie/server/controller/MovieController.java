package com.tao.movie.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tao.movie.server.feign.UserFeignClient;

/**
 * @Description 电影
 * @Author taoyinwu
 * @Date 2019/4/2 20:05
 */
@RequestMapping("/movie")
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @HystrixCommand
    @GetMapping("/user/{id}")
    public String findById(@PathVariable Long id) {

        String forObject = this.userFeignClient.findById(id);
        // ...电影微服务的业务...
        return forObject;
    }

}
