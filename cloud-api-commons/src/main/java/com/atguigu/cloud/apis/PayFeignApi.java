package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author FAll
 * @date 2024年11月14日 10:07
 */
@FeignClient("cloud-payment-service")
public interface PayFeignApi {
    /**
     * 新增一条支付相关流水记录
     * @param payDTO 支付DTO
     */
    @PostMapping("pay/add")
    ResultData<String> addPay(@RequestBody PayDTO payDTO);

    /**
     * 获取支付信息
     * @param id 支付信息id
     */
    @GetMapping("pay/get/{id}")
    ResultData<?> getPayInfo(@PathVariable("id") Integer id);

    /**
     * 获取consul信息
     */
    @GetMapping("pay/get/info")
    String mylb();


    @GetMapping("pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);
}
