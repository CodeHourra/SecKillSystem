package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * @author Liugan
 * @version V1.0
 * @date 2019/2/16 20:34
 * @Description 订单交易service
 */
public interface OrderService {

  /**
   * 创建订单
   * @param userId 用户Id
   * @param itemId 商品Id
   * @param amount 购买数量
   * @return 订单模型
   * @throws BusinessException
   */
  OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
