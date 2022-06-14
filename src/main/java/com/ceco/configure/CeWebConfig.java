package com.ccj.configure;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.ccj.common.convert.MultiRequestBodyArgumentResolver;
import com.ccj.common.convert.UrlRequestBodyMethodArgumentResolver;
import com.ccj.interceptor.ChannelInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Primary
@MapperScan("com.zf.module.*.dao")
@Configuration("zfWebConfig")
public class CeWebConfig implements WebMvcConfigurer {

    @Lazy
    @Autowired
    private ChannelInterceptor channelInterceptor;

    @Autowired
    private UrlRequestBodyMethodArgumentResolver urlRequestBodyMethodArgumentResolver;

    @Autowired
    private MultiRequestBodyArgumentResolver multiRequestBodyArgumentResolver;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(channelInterceptor).addPathPatterns("/**").excludePathPatterns(getExcludePathPatterns());
    }
    private ArrayList<String> getExcludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
            "/error",
            "/**/cache/**",
            "/swagger-ui.html",
                "/webjars/**",
                "/doc.html",
                "/h5/**"
        };
        Collections.addAll(list, urls);
        return list;
    }
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
    @Bean
    public ObjectMapper jsonMapper() {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return jsonMapper;
    }

    /**
     * 添加参数解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //多个json
        argumentResolvers.add(multiRequestBodyArgumentResolver);
        //get支持@RequestBody
        argumentResolvers.add(urlRequestBodyMethodArgumentResolver);
    }
}
