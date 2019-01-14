package com.miaoshaproject.service.impl;


import com.miaoshaproject.dao.UserInfoEntityMapper;
import com.miaoshaproject.dao.UserPasswordEntityMapper;
import com.miaoshaproject.dataobject.UserInfoEntity;
import com.miaoshaproject.dataobject.UserPasswordEntity;
import com.miaoshaproject.service.UserInfoService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/14 21:55
 * @Description TODO
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Autowired
  private UserInfoEntityMapper userInfoEntityMapper;

  @Autowired
  private UserPasswordEntityMapper userPasswordEntityMapper;

  /**
   * 根据用户ID获取用户信息
   * @param id 用户Id
   * @return 用户实体
   */
  @Override
  public UserModel getUserById(Integer id) {
    UserInfoEntity userInfoEntity = userInfoEntityMapper.selectByPrimaryKey(id);
    if (null == userInfoEntity) {
      return null;
    }
    UserPasswordEntity userPasswordEntity = userPasswordEntityMapper.selectByUserId(userInfoEntity.getId());
    return convertFromDataObject(userInfoEntity, userPasswordEntity);
  }

  /**
   * 实体model转换
   * @param userInfoEntity 用户信息实体
   * @param userPasswordEntity 用户密码实体
   * @return userModel
   */
  private UserModel convertFromDataObject(UserInfoEntity userInfoEntity, UserPasswordEntity userPasswordEntity) {
    if (null == userInfoEntity) {
      return null;
    }
    UserModel userModel = new UserModel();
    BeanUtils.copyProperties(userInfoEntity, userModel);
    if (null != userPasswordEntity) {
      userModel.setEncrptPassword(userPasswordEntity.getEncrptPassword());
    }
    return userModel;
  }
}
