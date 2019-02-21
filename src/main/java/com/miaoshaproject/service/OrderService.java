package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * @author Liugan
 * @version V1.0
 * @date 2019/2/16 20:34
 * @description 订单交易service
 */
public interface OrderService {

  /**
   * 创建订单
   * 存在两种方案：
   * 1. 通过前端传过来的活动id，在接口内校验对应id是否属于对应商品且活动已经开始 √
   * 2. 直接在下单接口中判断对应的商品是否存在秒杀活动，若存在进行中的活动则以秒杀价格下单
   * @param userId 用户Id
   * @param itemId 商品Id
   * @param amount 购买数量
   * @param promoId 活动Id
   * @return 订单模型
   * @throws BusinessException
   */
  OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
