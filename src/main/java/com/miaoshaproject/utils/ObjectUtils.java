package com.miaoshaproject.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/19 19:57
 * @Description 对象校验工具类
 */
@Component
public class ObjectUtils {

  public boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
    boolean flag = false;
    for (Field f : obj.getClass().getDeclaredFields()) {
      f.setAccessible(true);
      if (f.get(obj) == null || f.get(obj).equals("")) {
        flag = true;
      }
    }
    return flag;
  }
}
