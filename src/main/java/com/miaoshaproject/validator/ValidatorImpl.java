package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/2/12 20:56
 * @Description TODO
 */
@Component
public class ValidatorImpl implements InitializingBean {

  private Validator validator;

  /**
   * 实现校验方法并返回校验结果
   * @param bean
   * @return
   */
  public ValidationResult validationResult(Object bean) {
    final ValidationResult result = new ValidationResult();
    Set<ConstraintViolation<Object>> validate = validator.validate(bean);
    if (validate.size() > 0) {
      // hasError
      result.setHasErrors(true);
      validate.forEach(constraintViolation -> {
        String errMsg = constraintViolation.getMessage();
        String propertyName = constraintViolation.getPropertyPath().toString();
        result.getErrorMsgMap().put(propertyName, errMsg);
      });
    }
    return result;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // 将hibernate validator通过工厂的初始化方式使其实例化
    this.validator = Validation.buildDefaultValidatorFactory().getValidator();
  }
}
