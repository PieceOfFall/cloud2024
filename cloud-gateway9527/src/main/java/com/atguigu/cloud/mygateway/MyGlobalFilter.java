package com.atguigu.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @author FAll
 * @date 2024年11月21日 16:07
 */
@Component //不要忘记
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered
{

    public static final String BEGIN_VISIT_TIME = "begin_visit_time";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        exchange.getAttributes().put(BEGIN_VISIT_TIME,System.currentTimeMillis());
        return chain
                .filter(exchange)
                .then(Mono.fromRunnable(()->{
                    Long beginTime = exchange.getAttribute(BEGIN_VISIT_TIME);
                    if(beginTime != null) {
                        log.info("访问接口主机:" + exchange.getRequest().getURI().getHost());
                        log.info("访问接口端口:" + exchange.getRequest().getURI().getPort());
                        log.info("访问接口URL:" + exchange.getRequest().getURI().getPath());
                        log.info("访问接口Query:" + exchange.getRequest().getURI().getRawQuery());
                        log.info("访问接口时长:" + (System.currentTimeMillis() - beginTime) + "ms\n\n");
                    }
                }));
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}