package com.miaoshaproject.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/20 21:01
 * @Description 秒杀模型
 */
public class PromoModel {
  private Integer id;

  /**
   * 秒杀活动状态 1: 未开始 2:进行中 3: 已结束
   */
  private Integer status;

  /**
   * 秒杀活动名称
   */
  private String promoName;
  /**
   * 秒杀开始时间
   */
  private DateTime startDate;
  /**
   * 秒杀结束时间
   */
  private DateTime endTime;
  /**
   * 参加秒杀商品的Id
   */
  private Integer itemId;
  /**
   * 秒杀商品价格
   */
  private BigDecimal promoItemPrice;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPromoName() {
    return promoName;
  }

  public void setPromoName(String promoName) {
    this.promoName = promoName;
  }

  public DateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(DateTime startDate) {
    this.startDate = startDate;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public BigDecimal getPromoItemPrice() {
    return promoItemPrice;
  }

  public void setPromoItemPrice(BigDecimal promoItemPrice) {
    this.promoItemPrice = promoItemPrice;
  }

  public DateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(DateTime endTime) {
    this.endTime = endTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
