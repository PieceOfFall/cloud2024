package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
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

    private final PayFeignApi payFeignApi;

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
        return payFeignApi.mylb();
    }

    @GetMapping("pay/get/{id}")
    public ResultData<?> getOrder(@PathVariable("id") Integer id) {
        return payFeignApi.getPayInfo(id);
    }



    @PostMapping("pay/add")
    public ResultData<?> addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

}
