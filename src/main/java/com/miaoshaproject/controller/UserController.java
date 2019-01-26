package com.miaoshaproject.controller;


import com.alibaba.druid.util.StringUtils;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserInfoService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/14 21:36
 * @Description TODO
 */
@RestController()
@RequestMapping(value = "/users")
@CrossOrigin(allowCredentials="true",allowedHeaders = "*")
public class UserController extends BaseController {

  @Autowired
  UserInfoService userInfoService;

  @Autowired
  HttpServletRequest httpServletRequest;

  /**
   * 根据用户id查询用户信息
   *
   * @param id 用户id
   * @return 用户对象
   * @throws BusinessException
   */
  @GetMapping("/id")
  @ResponseBody
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

  /**
   * 根据手机号码获得otp验证码
   *
   * @param telphone
   * @return
   */
  @PostMapping(value = "/getotp", consumes = CONTENT_TYPE_FORMED)
  @ResponseBody
  public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone) {
    // 按照一定的规则生成otp验证码
    Random random = new Random();
    int randomInt = random.nextInt(99999);
    randomInt += 10000;
    String otpCode = String.valueOf(randomInt);

    // 将otp验证码同对应的手机号关联,使用httpsession的方式来绑定他的手机号和otpCode
    httpServletRequest.getSession().setAttribute(telphone, otpCode);
    // 将opt验证码通过短信通道发送给用户
    System.out.println("telephone =" + telphone + " & otpCode =" + otpCode);

    return CommonReturnType.create(true);
  }

  /**
   * 用户注册
   *
   * @param userVO
   * @return
   */
  @PostMapping(value = "/register", consumes = CONTENT_TYPE_JSON)
  @ResponseBody
  public CommonReturnType userRegister(@RequestBody @Validated UserVO userVO) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
    // 验证手机号和对应的otpCode相符合
    String otp = (String) this.httpServletRequest.getSession().getAttribute(userVO.getTelphone());
    if (!StringUtils.equals(userVO.getOtpCode(), otp)) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码错误");
    }
    // 用户的注册流程
    UserModel userModel = convertFromVO(userVO);
    userModel.setRegisterMode("byphone");
    userModel.setEncrptPassword(this.encodeByMd5(userVO.getPassword()));

    Boolean register = userInfoService.userRegister(userModel);

    return CommonReturnType.create(register);

  }

  /**
   * 用户登陆
   * @param userVO 传入参数
   * @return
   */
  @PostMapping(value = "/login", consumes = CONTENT_TYPE_JSON)
  @ResponseBody
  public CommonReturnType userLogin(@RequestBody UserVO userVO) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
    if (org.apache.commons.lang3.StringUtils.isEmpty(userVO.getTelphone())
        || org.apache.commons.lang3.StringUtils.isEmpty(userVO.getPassword())) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }
    // 用户登录服务，用来判断用户登录是否合法
    UserModel login = userInfoService.login(userVO.getTelphone(), this.encodeByMd5(userVO.getPassword()));
    this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
    this.httpServletRequest.getSession().setAttribute("LOGIN_USer", login);
    return CommonReturnType.create(true);
  }
  /**
   * Vo => Model
   *
   * @param userModel 用户model
   * @return
   */
  private UserVO convertFromModel(UserModel userModel) {
    if (null == userModel) {
      return null;
    }
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(userModel, userVO);
    return userVO;
  }

  private UserModel convertFromVO(UserVO userVO) {
    if (null == userVO) {
      return null;
    }
    UserModel userModel = new UserModel();
    BeanUtils.copyProperties(userVO, userModel);
    userModel.setGender(userVO.getGender().equals("1") ? new Byte("1") : new Byte("2"));
    return userModel;
  }

  /**
   * 密码加密
   * @param str 原密码
   * @return 加密后的密码
   * @throws NoSuchAlgorithmException
   * @throws UnsupportedEncodingException
   */
  private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    // 确定计算方法
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    BASE64Encoder base64Encoder = new BASE64Encoder();
    // 加密字符串
    String newStr = base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
    // 返回
    return newStr;
  }
}
