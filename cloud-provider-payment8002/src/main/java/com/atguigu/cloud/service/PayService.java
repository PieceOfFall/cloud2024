package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

/**
 * @author FAll
 * @date 2024/10/29 16:23
 */
public interface PayService {
    int add(Pay pay);
    int delete(Integer id);
    int update(Pay pay);

    Pay getById(int id);
    List<Pay> getAll();
}
