package com.miaoshaproject.controller;


import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserInfoService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/14 21:36
 * @Description TODO
 */
@RestController()
@RequestMapping(value = "/users")
public class UserController extends BaseController {

  @Autowired
  UserInfoService userInfoService;

  @GetMapping("/id")
  public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
    // 调用service服务获取对应的id返回给前端
    UserModel userInfo = userInfoService.getUserById(id);

    if (null == userInfo) {
      throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
    }

    // 将核心领域模型用户对象转化成UI可使用的viewObject
    UserVO userVO = convertFromModel(userInfo);

    return CommonReturnType.create(userVO);
  }

  private UserVO convertFromModel(UserModel userModel) {
    if (null == userModel) {
      return null;
    }
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(userModel, userVO);
    return userVO;
  }

}
