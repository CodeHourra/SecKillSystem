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
   * @param userModel 用户信息对象
   * @return 用户注册结果
   * @throws BusinessException
   */
  public Boolean userRegister(UserModel userModel) throws BusinessException;

  /**
   * 用户登录
   * @param telephone 电话
   * @param password 密码
   * @return
   * @throws BusinessException
   */
  public UserModel login(String telephone, String password) throws BusinessException;
}
