package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVo;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/12 21:57
 * @Description TODO
 */
@RestController("item")
@RequestMapping(value = "/item")
public class ItemController extends BaseController {

  @Autowired
  private ItemService itemService;

  /**
   * 创建商品的controller
   * @param itemVo
   * @return
   */
  @PostMapping(value = "/createItem", consumes = CONTENT_TYPE_JSON)
  @ResponseBody
  public CommonReturnType createItem(@RequestBody ItemVo itemVo) {
    // 封装service方法用来创建商品
    ItemModel itemModel = new ItemModel();
    ItemModel itemModelFromReturn = null;
    try {
      BeanUtils.copyProperties(itemVo, itemModel);
      itemModelFromReturn = itemService.createItem(itemModel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (itemModelFromReturn != null) {
      BeanUtils.copyProperties(itemModelFromReturn, itemVo);
    } else {
      itemVo = null;
    }
    return CommonReturnType.create(itemVo);
  }
  /**
   * 查询单个商品的controller
   * @param itemId
   * @return
   */
  @GetMapping(value = "/getItem")
  @ResponseBody
  public CommonReturnType getItem(@RequestParam Integer itemId) {
    // 封装service方法用来获取商品
    ItemModel item = itemService.getItemById(itemId);
    ItemVo itemVo = new ItemVo();
    if (item != null) {
      BeanUtils.copyProperties(item, itemVo);
    }
    return CommonReturnType.create(itemVo);
  }
  /**
   * 查询商品列表的controller
   * @return
   */
  @GetMapping(value = "/getItems")
  @ResponseBody
  public CommonReturnType getItems() {
    // 封装service方法用来获取商品
    List<ItemModel> itemModels = itemService.listItem();
    List<ItemVo> itemVoList = itemModels.stream().map(itemModel -> {
      ItemVo itemVo = new ItemVo();
      if (itemModel != null) {
        BeanUtils.copyProperties(itemModel, itemVo);
      }
      return itemVo;
    }).collect(Collectors.toList());

    return CommonReturnType.create(itemVoList);
  }
}
