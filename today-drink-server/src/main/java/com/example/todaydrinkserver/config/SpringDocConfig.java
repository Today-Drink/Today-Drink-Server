//package com.example.todaydrinkserver.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class SpringDocConfig {
//    @Bean
//    public GroupedOpenApi shopsApi(){
//        return GroupedOpenApi.builder()
//                .group("shops")
//                .pathsToMatch("/shops/**")
//                .build();
//    }
//    @Bean
//    public GroupedOpenApi menusApi(){
//        return GroupedOpenApi.builder()
//                .group("menus")
//                .pathsToMatch("/menus/**")
//                .build();
//    }
//    @Bean
//    public OpenAPI customOpenAPI(){
//        return new OpenAPI()
//                .info(new Info().title("Today-Drink API")
//                        .description("API documentation using SpringDoc and OpenAPI 3."));
//    }
//}
