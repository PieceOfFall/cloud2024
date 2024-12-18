package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @author FAll
 * @date 2024年11月11日 15:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("consumer")
public class OrderController {

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;
    public static final String PAYMENT_SRV_URL = "http://cloud-payment-service";

    @GetMapping("discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

    @GetMapping("pay/get/info")
    public String getInfo() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/info", String.class);
    }

    @GetMapping("pay/get/{id}")
    public ResultData<?> getOrder(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/" + id, ResultData.class);
    }

    @GetMapping("pay/get/all")
    public ResultData<?> getAllOrder() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/all", ResultData.class);
    }

    @PostMapping("pay/add")
    public ResultData<?> addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_SRV_URL + "/pay/add", payDTO, ResultData.class);
    }

    @PutMapping("pay/update")
    public ResultData<Void> updateOrder(@RequestBody PayDTO payDTO) {
        restTemplate.put(PAYMENT_SRV_URL + "/pay/update", payDTO);
        return ResultData.success();
    }

    @DeleteMapping("del/{id}")
    public ResultData<Void> deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PAYMENT_SRV_URL + "/pay/del/" + id);
        return ResultData.success();
    }
}
