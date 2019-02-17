package com.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/16 19:58
 * @Description 订单交易模型
 */
public class OrderModel {
  /**
   * 订单ID 按照某种规则生成
   */
  private String id;

  /**
   * 用户Id
   */
  private Integer userId;

  /**
   * 商品Id
   */
  private Integer itemId;

  /**
   * 购买商品时的单价
   */
  private BigDecimal itemPrice;

  /**
   * 购买数量
   */
  private Integer amount;

  /**
   * 购买金额
   */
  private BigDecimal orderPrice;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public Integer getAmount() {
    return amount;
  }

  public BigDecimal getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(BigDecimal itemPrice) {
    this.itemPrice = itemPrice;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public BigDecimal getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(BigDecimal orderPrice) {
    this.orderPrice = orderPrice;
  }
}
