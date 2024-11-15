package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FAll
 * @date 2024年11月15日 15:39
 */
@RestController
@RequiredArgsConstructor
public class OrderCircuitController {
    private final PayFeignApi payFeignApi;

    @GetMapping("consumer/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    //myCircuitFallback就是服务降级后的兜底处理方法
    public String myCircuitFallback(Integer id,Throwable throwable) {
        // 这里是容错处理逻辑，返回备用结果
        return "myCircuitFallback, 系统繁忙, 请稍后再试-----/(T。T)/~~";
    }
}
