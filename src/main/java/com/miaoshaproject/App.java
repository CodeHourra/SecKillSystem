package com.miaoshaproject;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 * @author liugan
 */
@Configuration
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
@ComponentScan(basePackages = "com.miaoshaproject")
@MapperScan("com.miaoshaproject.dao")
@RestController
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
