package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * @author FAll
 * @date 2024年11月11日 15:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("consumer/pay")
public class OrderController {

    private final RestTemplate restTemplate;
    public static final String PAYMENT_SRV_URL = "http://localhost:8001/";

    @GetMapping("get/{id}")
    public ResultData<?> getOrder(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "pay/get/" + id, ResultData.class);
    }

    @GetMapping("get/all")
    public ResultData<?> getAllOrder() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "pay/get/all", ResultData.class);
    }

    @PostMapping("add")
    public ResultData<?> addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_SRV_URL + "pay/add", payDTO, ResultData.class);
    }

    @PutMapping("update")
    public ResultData<Void> updateOrder(@RequestBody PayDTO payDTO) {
        restTemplate.put(PAYMENT_SRV_URL + "pay/update", payDTO);
        return ResultData.success();
    }

    @DeleteMapping("del/{id}")
    public ResultData<Void> deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PAYMENT_SRV_URL + "pay/del/" + id);
        return ResultData.success();
    }
}
