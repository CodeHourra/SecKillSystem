package com.miaoshaproject.controller.viewobject;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/14 22:51
 * @Description TODO
 */
public class UserVO {
  private Integer id;
  @NotNull(message = "{user.name.notBlank}")
  private String name;
  @NotNull(message = "{user.gender.notBlank}")
  private String gender;
  @Max(value = 120, message = "{user.age.max}")
  @Min(value = 0, message = "{user.age.min}")
  private Integer age;
  @NotNull(message = "{user.telephone.notBlank}")
  private String telphone;
  @NotNull(message = "{user.otpCode.notBlank}")
  private String otpCode;
  @NotNull(message = "{user.password.notBlank}")
  private String password;

  public String getOtpCode() {
    return otpCode;
  }

  public void setOtpCode(String otpCode) {
    this.otpCode = otpCode;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getTelphone() {
    return telphone;
  }

  public void setTelphone(String telphone) {
    this.telphone = telphone;
  }
}
