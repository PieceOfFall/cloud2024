package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author FAll
 * @date 2024年11月14日 13:11
 */
@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        // 最大请求次数3(1+2), 初始间隔时间100ms, 重试间最大间隔时间为1s
        return new Retryer.Default(100,1,3);
    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
