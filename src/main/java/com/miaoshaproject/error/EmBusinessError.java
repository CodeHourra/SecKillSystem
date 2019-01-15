package com.miaoshaproject.error;

/**
 * @author lumpcode
 * 错误信息枚举
 */
public enum EmBusinessError implements CommonError {
  // 通用错误类型10001
  PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
  UNKNOWN_ERROR(10002, "未知错误"),
  // 20000开头信息为用户信息错误码
  USER_NOT_EXIST(20001, "用户不存在")
  ;

  private int errCode;
  private String errMsg;

  /**
   * 构造方法
   * @param errCode
   * @param errMsg
   */
  private EmBusinessError(int errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  /**
   * 获得错误码
   *
   * @return
   */
  @Override
  public int getErrCode() {
    return this.errCode;
  }

  /**
   * 获得错误信息
   *
   * @return
   */
  @Override
  public String getErrMsg() {
    return this.errMsg;
  }

  /**
   * 设置错误信息
   *
   * @param errMsg
   * @return
   */
  @Override
  public CommonError setErrMsg(String errMsg) {
    this.errMsg = errMsg;
    return this;
  }
}
