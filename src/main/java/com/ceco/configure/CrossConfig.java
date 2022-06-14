package com.ceco.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: zy
 * @Date: 2020/8/31 13:56
 * @desc：
 */
@Configuration
public class CrossConfig {
    // 全局跨域配置： 可以在java后台配置，也可以在vue前台配置
    private CorsConfiguration addCorsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", addCorsConfig());
        return new CorsFilter(source);
    }
}
