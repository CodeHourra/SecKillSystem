package com.miaoshaproject.controller.viewobject;

import java.math.BigDecimal;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/12 21:59
 * @Description TODO
 */
public class ItemVo {
  private Integer id;

  /**
   * 商品名称
   */
  private String title;

  /**
   * 商品价格
   */
  private BigDecimal price;

  /**
   * 商品库存
   */
  private Integer stock;

  /**
   * 商品描述
   */
  private String description;

  /**
   * 商品销量
   */
  private Integer sales;

  /**
   * 商品图片
   */
  private String imgUrl;

  /**
   * 秒杀状态
   */
  private Integer promoStatus;

  /**
   * 秒杀价格
   */
  private BigDecimal promoPrice;

  /**
   * 秒杀活动Id
   */
  private Integer promoId;

  /**
   * 秒杀开始时间
   */
  private String startTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getSales() {
    return sales;
  }

  public void setSales(Integer sales) {
    this.sales = sales;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Integer getPromoStatus() {
    return promoStatus;
  }

  public void setPromoStatus(Integer promoStatus) {
    this.promoStatus = promoStatus;
  }

  public BigDecimal getPromoPrice() {
    return promoPrice;
  }

  public void setPromoPrice(BigDecimal promoPrice) {
    this.promoPrice = promoPrice;
  }

  public Integer getPromoId() {
    return promoId;
  }

  public void setPromoId(Integer promoId) {
    this.promoId = promoId;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
}
