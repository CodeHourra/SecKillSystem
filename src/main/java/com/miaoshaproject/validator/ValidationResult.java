package com.miaoshaproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/12 20:50
 * @Description 自定义校验封装类
 */
public class ValidationResult {
  /**
   * 校验结果是否有错
   */
  private boolean hasErrors = false;

  /**
   * 存放错误信息
   */
  private Map<String, String> errorMsgMap = new HashMap<>();

  public boolean isHasErrors() {
    return hasErrors;
  }

  public void setHasErrors(boolean hasErrors) {
    this.hasErrors = hasErrors;
  }

  public Map<String, String> getErrorMsgMap() {
    return errorMsgMap;
  }

  public void setErrorMsgMap(Map<String, String> errorMsgMap) {
    this.errorMsgMap = errorMsgMap;
  }

  /**
   * 通用的格式化字符串信息返回错误结果的message的方法
   * @return
   */
  public String getErrorMsg() {
    return StringUtils.join(errorMsgMap.values().toArray(), ",");
  }
}
