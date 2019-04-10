package com.tao.movie.server.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.hutool.log.StaticLog;
import feign.hystrix.FallbackFactory;

/**
 * @Description 用户
 * @Author taoyinwu
 * @Date 2019/4/3 18:00
 */
@FeignClient(name = "user-server", fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {
    @GetMapping("/user/{id}")
    String findById(@PathVariable("id") Long id);

    @GetMapping("/user/testConfig")
    String testConfig();

}

@Component
class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public String findById(Long id) {
                StaticLog.error(throwable, "findById降级处理");
                return "findByIdFallback";
            }

            @Override
            public String testConfig() {
                StaticLog.error(throwable, "testConfig降级处理");
                return "testConfig";
            }
        };
    }
}