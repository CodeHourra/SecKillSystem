package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/14 21:47
 * @Description 用户信息service接口
 */
public interface UserInfoService {
  /**
   * 根据用户ID获取用户信息
   * @param Id 用户Id
   * @return 用户实体
   */
  public UserModel getUserById(Integer Id);

  /**
   * 用户注册
   * @param userModel
   * @return
   */
  public Boolean userRegister(UserModel userModel) throws BusinessException;
}
