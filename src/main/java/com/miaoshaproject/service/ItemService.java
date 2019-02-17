package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

/**
 * @author Liugan
 * @version V1.0
 * @date 2019/2/12 20:34
 * @Description 商品service
 */
public interface ItemService {
  /**
   * 创建商品
   * @param itemModel 商品对象
   * @return
   */
  ItemModel createItem(ItemModel itemModel) throws BusinessException;

  /**
   * 商品列表浏览
   * @return
   */
  List<ItemModel> listItem();

  /**
   * 商品详情浏览
   * @param id 商品id
   * @return
   */
  ItemModel getItemById(Integer id);

  /**
   * 根据商品ID获取商品库存
   * @param id 商品id
   * @return 商品库存
   */
  Integer getItemStockById(Integer id);

  /**
   * 扣减商品数量
   * @param id 商品ID
   * @param amount 购买数量
   * @return 是否成功
   * @throws BusinessException
   */
  Boolean decreaseStock(Integer id, Integer amount) throws BusinessException;
}
