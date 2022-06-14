package com.ceco.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildSysDocket() {
        List<Parameter> aParameters = getParameters();

        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("美食美客直播API接口文档")
                .groupName("管理端API接口文档")
                .apiInfo(buildApiInf())
                .globalOperationParameters(aParameters)
//        .host("localhost:20000")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ceco.channel.admin"))//controller路径
                .paths(PathSelectors.any()).build();
    }
    @Bean
    public Docket buildH5Docket() {
        List<Parameter> aParameters = getParameters();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("H5API接口文档")
                .apiInfo(buildApiInf())
//        .host("localhost:20000")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ceco.channel.app"))//controller路径
                .paths(PathSelectors.any()).build();
    }

    private List<Parameter> getParameters() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("Authorization") //参数名
                .defaultValue("eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMjcyNzgxNDU5NDIwNTQ1MDI1Iiwibm9uY2UiOiI2NzI0YjRiNy1kM2U5LTQ3YmMtYjBhNS0zODMzOGJlYmM2NmYiLCJ1aWQiOiIxMjcyNzgxNDU5NDIwNTQ1MDI1IiwidW5hbWUiOiLnpZ3li4ciLCJtb2JpbGUiOiIxODI3NDc1NDYxMyIsImV4cCI6MTkwNzcxNjUxMn0.ZmsiTDgg8NBpgZQnG_SEBA7x8UVTpBtwq6vFO14fBzUB3s4bogdZ-EDMft919vaoYxyPcE9PbqKRisB5DtOhM3ocVPhEXb8Z7EQbEoGpbKG3XbsiAw23U3F5z__PNyIPhr26wC_3mHfNSiPYGh5ZE0omZXrzAIC-8LMUwpxiaDA") //默认值
                .description("token令牌")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        return aParameters;
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("物联网平台")
                .description("物联网平台")
                .version("1.0")
                .build();

    }
}
