package com.tao.movie.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description 电影
 * @Author taoyinwu
 * @Date 2019/4/2 20:05
 */
@RequestMapping("/movie")
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public String findById(@PathVariable Long id) {

        // 这里用到了RestTemplate的占位符能力
        String forObject = this.restTemplate.getForObject("http://user-service/user/{id}", String.class, id);
        // ...电影微服务的业务...
        return forObject;
    }
}
