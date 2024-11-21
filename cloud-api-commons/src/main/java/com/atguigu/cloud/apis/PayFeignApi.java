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
@FeignClient("cloud-gateway")
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

    /**
     * Resilience Bulkhead 的例子
     * @param id 支付信息id
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id 支付信息id
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     * @param id 支付信息id
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData<?> getById(@PathVariable("id") Integer id);

    /**
     * Gateway进行网关测试案例02
     */
    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();
}
