package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

public interface IOrderService {

    ServerResponse pay(long orderNo, Integer userId, String path);

    ServerResponse aliCallBack(Map<String, String> params);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    ServerResponse create(Integer userId, Integer shippingId);

    ServerResponse cancel(Integer userId,Long orderNo);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse getOrderDetail(Integer userId,Long orderNo);

    ServerResponse getOrderList(Integer userId,int pageNum,int pageSize);

    // backend
    ServerResponse manageList(int pageNum,int pageSize);
    ServerResponse manageDetail(Long orderNo);
    ServerResponse manageSearch(Long orderNo, int pageNum, int pageSize);
    ServerResponse manageSendGoods(Long orderNo);
}
