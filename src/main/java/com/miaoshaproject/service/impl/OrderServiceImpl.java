package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderInfoEntityMapper;
import com.miaoshaproject.dao.SequenceInfoEntityMapper;
import com.miaoshaproject.dataobject.OrderInfoEntity;
import com.miaoshaproject.dataobject.SequenceInfoEntity;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserInfoService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/16 20:41
 * @Description TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private SequenceInfoEntityMapper sequenceInfoEntityMapper;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserInfoService userInfoService;

  @Autowired
  private OrderInfoEntityMapper orderInfoEntityMapper;

  /**
   * 创建订单
   *
   * @param userId 用户Id
   * @param itemId 商品Id
   * @param amount 购买数量
   * @return 订单模型
   */
  @Override
  @Transactional
  public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
    // 1.校验下单状态，下单商品是否存在，用户是否合法，购买数量是否正确
    ItemModel itemModel = itemService.getItemById(itemId);
    if (itemModel == null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
    }
    UserModel userModel = userInfoService.getUserById(userId);
    if (userModel == null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
    }
    int stock = itemService.getItemStockById(itemId);
    if (amount <= 0 || amount >= stock) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "购买数量不正确");

    }
    // 2.落单减库存(或者 支付减库存)
    Boolean result = itemService.decreaseStock(itemId, amount);
    if (!result) {
      throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
    }
    // 3.订单入库
    OrderModel orderModel = new OrderModel();
    orderModel.setUserId(userId);
    orderModel.setItemId(itemId);
    orderModel.setAmount(amount);
    orderModel.setItemPrice(itemModel.getPrice());
    orderModel.setOrderPrice(itemModel.getPrice().multiply(BigDecimal.valueOf(amount)));

    // 生成交易流水号(订单号)
    orderModel.setId(generateOrderNo());
    OrderInfoEntity orderInfoEntity = convertFromOrderModel(orderModel);

    int i = orderInfoEntityMapper.insertSelective(orderInfoEntity);
    // 4.返回前端
    return orderModel;
  }

  /**
   * 生成唯一订单号
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  String generateOrderNo() {
    // 订单号16位
    StringBuilder orderNo = new StringBuilder();
    // 前8位 年月日
    LocalDateTime now = LocalDateTime.now();
    String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
    orderNo.append(nowDate);
    // 中间6位自增 序列
    // 获取当前sequence
    int sequenceNo = 0;
    SequenceInfoEntity sequence = sequenceInfoEntityMapper.getSequenceByName("order_info");
    sequenceNo = sequence.getCurrentValue();
    int currentValue = sequence.getCurrentValue() + sequence.getStep();
    if (currentValue > sequence.getMaxValue()) {
      currentValue = sequence.getInitValue();
      sequence.setCurrentValue(sequence.getInitValue());
    }
    sequence.setCurrentValue(sequence.getCurrentValue() + sequence.getStep());
    sequenceInfoEntityMapper.updateByPrimaryKeySelective(sequence);
    String sequenceStr = String.valueOf(sequenceNo);
    for (int i = 0; i < 6 - sequenceStr.length(); i++) {
      orderNo.append(0);
    }
    orderNo.append(sequenceStr);
    // 最后两位分库分表位,暂时写死
    orderNo.append("00");

    return orderNo.toString();
  }

  private OrderInfoEntity convertFromOrderModel(OrderModel orderModel) {
    if (orderModel == null) {
      return null;
    }
    OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
    BeanUtils.copyProperties(orderModel, orderInfoEntity);
    return orderInfoEntity;
  }
}
