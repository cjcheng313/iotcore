package com.ceco.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @Author: zy
 * @Date: 2020/8/24 9:23
 * @desc：
 */
@Configuration
public class FileUploadConfiuration {
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        //单个文件大小200mb
        factory.setMaxFileSize(DataSize.ofGigabytes(10L));
        //设置总上传数据大小10GB
        factory.setMaxRequestSize(DataSize.ofGigabytes(20L));

        return factory.createMultipartConfig();
    }
}
