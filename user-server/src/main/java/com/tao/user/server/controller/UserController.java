package com.tao.user.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tao.user.server.service.impl.UserServiceImpl;

/**
 * @Description 用户
 * @Author taoyinwu
 * @Date 2019/4/2 20:05
 */
@RequestMapping("/user")
@RestController
@RefreshScope
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @HystrixCommand
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {

        return this.userService.findById(id);
    }

}
