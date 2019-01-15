package com.miaoshaproject.error;

/**
 * 错误集合类
 * @author lumpcode
 */
public interface CommonError {
  /**
   * 获得错误码
   * @return
   */
  public int getErrCode();

  /**
   * 获得错误信息
   * @return
   */
  public String getErrMsg();

  /**
   * 设置错误信息
   * @param errMsg
   * @return
   */
  public CommonError setErrMsg(String errMsg);
}
