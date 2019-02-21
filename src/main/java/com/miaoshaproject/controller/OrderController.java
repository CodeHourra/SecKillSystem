package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/17 22:42
 * @Description 订单Controller
 */
@RestController("order")
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
  @Autowired
  private OrderService orderService;

  @Autowired
  private HttpServletRequest httpServletRequest;

  /**
   * 创建商品的controller
   *
   * @param itemId 商品ID
   * @param amount 购买数量
   * @return
   */
  @PostMapping(value = "/createOrder", consumes = CONTENT_TYPE_FORMED)
  @ResponseBody
  public CommonReturnType createOrder(@RequestParam("itemId") Integer itemId,
                                      @RequestParam(value = "promoId", required = false) Integer promoId,
                                      @RequestParam("amount") Integer amount) throws BusinessException {
    // 封装service方法用来创建订单
    Boolean is_login = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
    if (is_login == null || !is_login.booleanValue()) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户未登录，无法下单");
    }
    // 获取用户登录信息
    UserModel login_user = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
    OrderModel orderModel = orderService.createOrder(login_user.getId(), itemId, promoId, amount);
    return CommonReturnType.create(orderModel);
  }
}
