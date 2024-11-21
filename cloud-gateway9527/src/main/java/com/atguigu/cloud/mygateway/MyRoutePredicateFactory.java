package com.atguigu.cloud.mygateway;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 根据会员等级，按照yaml中的配置，来检查是否可以访问
 * @author FAll
 * @date 2024年11月21日 10:02
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            String userType = serverWebExchange
                    .getRequest()
                    .getQueryParams()
                    .getFirst("userType");
            if(userType == null) return false;
            return userType.equalsIgnoreCase(config.getUserType());
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    @Getter
    @Validated
    public static class Config {
        @Setter
        @NotNull
        private String userType;
    }
}
