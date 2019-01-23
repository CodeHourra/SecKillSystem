package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/16 1:03
 * @Description Controller基类
 */
public class BaseController {
  public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
  /**
   * 定义exception Handler解决未被controller吸收的exception
   * @param request 请求
   * @param ex 异常
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Object handlerException(HttpServletRequest request, Exception ex) {
    HashMap<String, Object> responseData = new HashMap<String, Object>(16);
    if (ex instanceof BusinessException) {
      BusinessException businessException = (BusinessException) ex;

      responseData.put("errCode", businessException.getErrCode());
      responseData.put("errMsg", businessException.getErrMsg());
    } else {
      responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
      responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
    }

    return CommonReturnType.create(responseData, "fail");
  }
}
