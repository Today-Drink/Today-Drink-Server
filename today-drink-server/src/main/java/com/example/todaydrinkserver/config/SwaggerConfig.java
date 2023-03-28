package com.example.todaydrinkserver.config;

import com.example.todaydrinkserver.shop.ShopController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket shopApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("shops")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.todaydrinkserver.shop"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    @Bean
    public Docket menuApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("menus")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.todaydrinkserver.menu"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("users")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.todaydrinkserver.user"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Today Drink Project API")
                .description("API documentation for Today Drink Project")
                .version("1.0.0")
                .build();
    }
}
