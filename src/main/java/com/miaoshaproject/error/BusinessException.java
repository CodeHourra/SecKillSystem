package com.miaoshaproject.error;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/15 22:53
 * @Description 包装器业务异常类实现
 */
public class BusinessException extends Exception implements CommonError {

  private CommonError commonError;

  /**
   * 直接接受EmBusinessError的传参用于构造业务异常
   * @param commonError
   */
  public BusinessException(CommonError commonError) {
    super();
    this.commonError = commonError;
  }

  /**
   * 接受自定义业务异常
   * @param commonError
   * @param errMsg 自定义异常信息
   */
  public BusinessException(CommonError commonError, String errMsg) {
    super();
    this.commonError = commonError;
    this.commonError.setErrMsg(errMsg);
  }

  /**
   * 获得错误码
   *
   * @return
   */
  @Override
  public int getErrCode() {
    return this.commonError.getErrCode();
  }

  /**
   * 获得错误信息
   *
   * @return
   */
  @Override
  public String getErrMsg() {
    return this.commonError.getErrMsg();
  }

  /**
   * 设置错误信息
   *
   * @param errMsg
   * @return
   */
  @Override
  public CommonError setErrMsg(String errMsg) {
    this.commonError.setErrMsg(errMsg);
    return this;
  }
}
