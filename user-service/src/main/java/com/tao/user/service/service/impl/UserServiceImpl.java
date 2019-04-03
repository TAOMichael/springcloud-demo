package com.tao.user.service.service.impl;

import org.springframework.stereotype.Service;

import com.tao.user.service.service.UserService;

import cn.hutool.log.StaticLog;

/**
 * @Description TODO
 * @Author taoyinwu
 * @Date 2019/4/2 20:09
 */
@Service
public class UserServiceImpl implements UserService {

    public String findById(Long id) {

        StaticLog.info("调用UserService方法findById");

        return "user";
    };

}
