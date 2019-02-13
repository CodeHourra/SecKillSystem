package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemEntityMapper;
import com.miaoshaproject.dao.ItemStockEntityMapper;
import com.miaoshaproject.dataobject.ItemEntity;
import com.miaoshaproject.dataobject.ItemStockEntity;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/12 20:39
 * @Description 商品service实现
 */
@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  private ValidatorImpl validator;

  @Autowired
  private ItemEntityMapper itemEntityMapper;

  @Autowired
  private ItemStockEntityMapper itemStockEntityMapper;

  /**
   * itemModel -> dataobject
   * @param itemModel
   * @return
   */
  private ItemEntity convertItemEntityFromItemModel(ItemModel itemModel) {
    if (itemModel == null) {
      return null;
    }
    ItemEntity itemEntity = new ItemEntity();
    BeanUtils.copyProperties(itemModel,  itemEntity);
    itemEntity.setPrice(itemModel.getPrice().doubleValue());
    return itemEntity;
  }

  private ItemStockEntity convertItemStockEntityFromItemStockModel(ItemModel itemModel) {
    if (itemModel == null) {
      return null;
    }
    ItemStockEntity itemStockEntity = new ItemStockEntity();
    itemStockEntity.setItemId(itemModel.getId());
    itemStockEntity.setStock(itemModel.getStock());
    return itemStockEntity;
  }

  /**
   * 创建商品
   *
   * @param itemModel 商品对象
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public ItemModel createItem(ItemModel itemModel) throws BusinessException {
    // 校验入参
    ValidationResult result = validator.validationResult(itemModel);
    if (result.isHasErrors()) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrorMsg());
    }
    // 转化itemModel -> dataobject
    ItemEntity itemEntity = this.convertItemEntityFromItemModel(itemModel);
    // 写入数据库
    itemEntityMapper.insertSelective(itemEntity);
    itemModel.setId(itemEntity.getId());

    ItemStockEntity itemStockEntity = this.convertItemStockEntityFromItemStockModel(itemModel);

    itemStockEntityMapper.insertSelective(itemStockEntity);
    // 返回创建完成的对象
    return this.getItemById(itemModel.getId());
  }

  /**
   * 商品列表浏览
   *
   * @return
   */
  @Override
  public List<ItemModel> listItem() {
    return null;
  }

  /**
   * 商品详情浏览
   *
   * @param id 商品id
   * @return
   */
  @Override
  public ItemModel getItemById(Integer id) {
    ItemEntity item = itemEntityMapper.selectByPrimaryKey(id);
    if (item == null) {
      return null;
    }
    // 获取库存数量
    ItemStockEntity itemStockEntity = itemStockEntityMapper.selectByItemId(item.getId());
    // 将entity -> model
    ItemModel itemModel = convertModelFromDataObject(item, itemStockEntity);
    return itemModel;
  }

  private ItemModel convertModelFromDataObject(ItemEntity itemEntity, ItemStockEntity itemStockEntity) {
    ItemModel itemModel = new ItemModel();
    BeanUtils.copyProperties(itemEntity, itemModel);
    itemModel.setPrice(new BigDecimal(itemEntity.getPrice()));
    itemModel.setStock(itemStockEntity.getStock());
    return itemModel;
  }
}
