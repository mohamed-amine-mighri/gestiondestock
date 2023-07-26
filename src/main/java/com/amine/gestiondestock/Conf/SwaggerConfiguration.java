//package com.amine.gestiondestock.Conf;
//
//import static com.amine.gestiondestock.utils.Constants.APP_ROOT;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@ComponentScan(basePackages = {"com.amine.gestiondestock"})
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration {
//
////  public static final String AUTHORIZATION_HEADER = "Authorization";
//
//  @Bean
//  public Docket api() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .apiInfo(
//            new ApiInfoBuilder()
//                .description("Gestion de stock API documentation")
//                .title("Gestion de stock REST API")
//                .build()
//        )
//        .groupName("REST API V1")
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("com.amine.gestiondestock"))
//        .paths(PathSelectors.any())
//        .build();
//  }
//
//}
