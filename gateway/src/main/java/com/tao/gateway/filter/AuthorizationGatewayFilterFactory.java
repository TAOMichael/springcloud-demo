package com.tao.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.tao.common.vo.ResponseVO;

import cn.hutool.json.JSONUtil;
import reactor.core.publisher.Mono;

/**
 * @Description TODO
 * @Author taoyinwu
 * @Date 2019/4/11 15:45
 */
@Component
public class AuthorizationGatewayFilterFactory
        extends AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config> {
    private static final String KEY = "withParams";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public AuthorizationGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            String token = exchange.getRequest().getHeaders().getFirst("token");
            // 校验token的合法性
            boolean tokenValidated = true;
            if (tokenValidated) {
                // 令牌合法，继续访问，可以进行一些处理，如：添加头信息，参数等
                ServerHttpRequest.Builder builder = exchange.getRequest().mutate();

                // 比如，根据令牌获得用户信息，userId

                return chain.filter(exchange.mutate().request(builder.build()).build());
            }
            // 令牌不合法
            ServerHttpResponse response = exchange.getResponse();

            // 设置headers
            HttpHeaders httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            // 过滤器中跨域需要自己处理
            // 设置body

            ResponseVO responseVO = new ResponseVO(-1000, "鉴权失败");

            DataBuffer bodyDataBuffer = response.bufferFactory()
                    .wrap(JSONUtil.toJsonStr(responseVO).getBytes(StandardCharsets.UTF_8));

            return response.writeWith(Mono.just(bodyDataBuffer));
        };
    }

    public static class Config {

        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }

    }
}
