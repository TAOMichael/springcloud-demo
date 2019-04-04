package com.tao.movie.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description 用户
 * @Author taoyinwu
 * @Date 2019/4/3 18:00
 */
@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    String  findById(@PathVariable("id") Long id);
}
