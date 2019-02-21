package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
 * @author Liugan
 * @date 2019/2/20 21点50分
 */
public interface PromoService {
  /**
   * 根据商品Id获取商品秒杀活动
   * @param itemId
   * @return
   */
  PromoModel getPromoByItemId(Integer itemId);
}
