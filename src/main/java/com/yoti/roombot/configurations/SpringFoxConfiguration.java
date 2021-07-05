package com.yoti.roombot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Collections.emptyList;

@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(new ApiInfo(
            "Yoti Back-end Test",
            "Imaginary Robotic Hoover",
            "1.0.0",
            null, new Contact("Federico Franceschetti", "https://github.com/franceschettif/Yoti-SDK-Back-end-test.git", "franceschetti.fdr@gmail.com"),
            null,
            null,
            emptyList()
        ))
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build()
        .enableUrlTemplating(false)
        ;
  }


}
