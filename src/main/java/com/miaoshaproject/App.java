package com.miaoshaproject;


import com.miaoshaproject.dao.UserInfoEntityMapper;
import com.miaoshaproject.dataobject.UserInfoEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Hello world!
 *
 * @author liugan
 */
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
@MapperScan("com.miaoshaproject.dao")
@RestController
public class App {
  /**
   * @Autowired 会报错，但是不影响编译运行，@Resource效果与@Autowired相同
   */
  @Resource
  private UserInfoEntityMapper userInfoEntityMapper;

  public static void main(String[] args) {
    System.out.println("Hello World!");
    SpringApplication.run(App.class, args);
  }

  @RequestMapping("/")
  public String home() {
    UserInfoEntity userInfoEntity = userInfoEntityMapper.selectByPrimaryKey(1);
    if (null == userInfoEntity) {
      return "No UserINfo";
    } else {
      return userInfoEntity.toString();
    }
  }

  public UserInfoEntityMapper getUserInfoEntityMapper() {
    return userInfoEntityMapper;
  }

  public void setUserInfoEntityMapper(UserInfoEntityMapper userInfoEntityMapper) {
    this.userInfoEntityMapper = userInfoEntityMapper;
  }
}
