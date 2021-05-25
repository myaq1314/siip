package com.pay.siip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : czh
 * description :
 * date : 2021-05-25
 * email 916419307@qq.com
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("贵州省农信行业应用平台API")
                .contact(new Contact("贵州省农信行业应用平台API", "https://www.gznxbank.com/", "916419307@qq.com"))
                .description("贵州省农信行业应用平台API")
                .termsOfServiceUrl("https://www.gznxbank.com/")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.pay.siip.api"))
                .paths(PathSelectors.any())
                .build();
    }
}
