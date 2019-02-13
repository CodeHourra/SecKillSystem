package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVo;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/12 21:57
 * @Description TODO
 */
@RestController()
@RequestMapping(value = "/item")
public class ItemController extends BaseController {

  @Autowired
  private ItemService itemService;

  /**
   * 创建商品的controller
   * @param itemVo
   * @return
   */
  public CommonReturnType createItem(@RequestBody ItemVo itemVo) {
    // 封装service方法用来创建商品
  }
}
