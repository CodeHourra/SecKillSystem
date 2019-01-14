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

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
