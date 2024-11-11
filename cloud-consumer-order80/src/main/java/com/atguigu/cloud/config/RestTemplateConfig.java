package com.atguigu.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author FAll
 * @date 2024年11月11日 15:39
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public  RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
