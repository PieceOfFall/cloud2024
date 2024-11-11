package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "支付微服务模块")
public class PayController {

    private final PayService payService;

    @PostMapping("add")
    @Operation(summary = "新增",description = "新增支付流水方法")
    public ResultData<String> add(@RequestBody Pay pay){
        return ResultData.success("成功插入记录, 返回值: " + payService.add(pay));
    }

    @DeleteMapping("del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> delete(@PathVariable("id") Integer id) {
        return ResultData.success(payService.delete(id));
    }

    @PutMapping("update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> update(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        return ResultData.success("成功修改记录, 返回值:" + payService.update(pay));
    }

    @GetMapping("get/{id}")
    @Operation(summary = "查询",description = "根据id查询单条支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        return ResultData.success(payService.getById(id));
    }

    @GetMapping("get/all")
    @Operation(summary = "查询所有",description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAll(){
        return ResultData.success(payService.getAll());
    }
}
