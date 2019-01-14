package com.miaoshaproject.controller;


import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.dataobject.UserInfoEntity;
import com.miaoshaproject.service.UserInfoService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UserController {

  @Autowired
  UserInfoService userInfoService;

  @GetMapping("/id")
  public ResponseEntity<?> getUser(@RequestParam(name = "id") Integer id) {
    // 调用service
    UserModel userInfo = userInfoService.getUserById(id);
    UserVO userVO = new UserVO();
    convertFromModel(userInfo, userVO);
    return new ResponseEntity<UserVO>(userVO, HttpStatus.OK);
  }
  private UserVO convertFromModel(UserModel userModel, UserVO userVO) {
    if (null == userModel) {
      return null;
    }
    BeanUtils.copyProperties(userModel, userVO);
    return userVO;
  }
}
