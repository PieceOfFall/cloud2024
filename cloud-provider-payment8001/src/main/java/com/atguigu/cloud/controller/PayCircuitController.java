package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author FAll
 * @date 2024年11月15日 11:13
 */
@Slf4j
@RestController
public class PayCircuitController {

    @GetMapping("pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if(id == -4) throw new RuntimeException("---circuit id 不能为负数");
        if(id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        return "Hello, circuit! InputID: " + id + "\t" + IdUtil.simpleUUID();
    }
}
