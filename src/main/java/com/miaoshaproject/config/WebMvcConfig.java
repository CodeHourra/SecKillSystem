package com.miaoshaproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/24 23:23
 * @Description 跨域配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedOrigins("*")
        .allowedHeaders("*")
        .allowedMethods("GET", "POST", "DELETE", "PUT")
        .maxAge(3600);
  }
}
