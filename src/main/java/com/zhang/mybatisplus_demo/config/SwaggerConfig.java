package com.zhang.mybatisplus_demo.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangzq
 */
@Configuration //注册为spring的bean
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    /**
     * 配置swagger的docket的bean实例
     *
     * @return
     */
    @Bean
    public Docket docket(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo("结合MybatisPlus"))
                //分组名称
                .groupName("MybatisPlus-Swagger-Demo")
                .select()
                //RequestHandlerSelectors:配置要扫描接口（controller）的方式
                //basePackage:指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.zhang.mybatisplus_demo.controller"))
                //过滤什么路径
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

        //配置Swagger信息
    }

    /**
     * apiInfo的构造方法，显示在页面上是标题
     *
     * @param title
     * @return
     */
    private ApiInfo apiInfo(String title) {
        return new ApiInfoBuilder().title(title)
                //标题下面的描述
                .description("<div style='font-size:14px;color:red;'>第一个Swagger RESTful APIs</div>")
                .build();
    }

}
