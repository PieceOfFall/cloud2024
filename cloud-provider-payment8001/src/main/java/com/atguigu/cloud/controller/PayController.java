package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FAll
 * @date 2024/10/29 16:34
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("pay")
public class PayController {

    private final PayService payService;

    @PostMapping("add")
    public String add(@RequestBody Pay pay){
        System.out.println(pay);
        return "成功插入记录, 返回值: " + payService.add(pay);
    }

    @DeleteMapping("del/{id}")
    public Integer delete(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping("update")
    public String update(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        return "成功修改记录, 返回值:" + payService.update(pay);
    }

    @GetMapping("get/{id}")
    public Pay getById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping("get/all")
    public List<Pay> getAll(){
        return payService.getAll();
    }
}
