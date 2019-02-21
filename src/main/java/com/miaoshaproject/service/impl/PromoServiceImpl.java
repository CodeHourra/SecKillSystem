package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.PromoEntityMapper;
import com.miaoshaproject.dataobject.PromoEntity;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/20 21:53
 * @Description TODO
 */
@Service
public class PromoServiceImpl implements PromoService {

  @Autowired
  private PromoEntityMapper promoEntityMapper;

  /**
   * 根据商品Id获取商品秒杀活动
   *
   * @param itemId
   * @return
   */
  @Override
  public PromoModel getPromoByItemId(Integer itemId) {
    // 获取对应商品的秒杀活动信息
    PromoEntity promoEntity = promoEntityMapper.selectByItemId(itemId);
    // dataObject -> model
    PromoModel promoModel = convertFromDataObject(promoEntity);
    if (promoModel == null) {
      return null;
    }
    // 判断秒杀活动状态
    DateTime now = new DateTime();
    if (promoModel.getStartDate().isAfterNow()) {
      promoModel.setStatus(1);
    } else if (promoModel.getEndTime().isBeforeNow()) {
      promoModel.setStatus(3);
    } else {
      promoModel.setStatus(2);
    }

    return promoModel;
  }

  private PromoModel convertFromDataObject(PromoEntity promoEntity) {
    if (promoEntity == null) {
      return null;
    }
    PromoModel promoModel = new PromoModel();
    BeanUtils.copyProperties(promoEntity, promoModel);
    promoModel.setPromoItemPrice(new BigDecimal(promoEntity.getPromoItemPrice()));
    promoModel.setStartDate(new DateTime(promoEntity.getStartDate()));
    promoModel.setEndTime(new DateTime(promoEntity.getEndDate()));
    return promoModel;
  }
}
