package com.leo.hogwarts.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.leo.hogwarts.interceptor.SourceAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebConfiguration
 * @Description
 * @Author Leo
 * @Date 2020/3/31 16:38
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        mediaTypes.add(MediaType.APPLICATION_JSON);
//        mediaTypes.add(MediaType.APPLICATION_ATOM_XML);
//        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
//        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
//        mediaTypes.add(MediaType.APPLICATION_PDF);
//        mediaTypes.add(MediaType.APPLICATION_RSS_XML);
//        mediaTypes.add(MediaType.APPLICATION_XHTML_XML);
//        mediaTypes.add(MediaType.APPLICATION_XML);
//        mediaTypes.add(MediaType.IMAGE_GIF);
//        mediaTypes.add(MediaType.IMAGE_JPEG);
//        mediaTypes.add(MediaType.IMAGE_PNG);
//        mediaTypes.add(MediaType.TEXT_EVENT_STREAM);
//        mediaTypes.add(MediaType.TEXT_HTML);
//        mediaTypes.add(MediaType.TEXT_MARKDOWN);
//        mediaTypes.add(MediaType.TEXT_PLAIN);
//        mediaTypes.add(MediaType.TEXT_XML);
//        converter.setSupportedMediaTypes(mediaTypes);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SourceAccessInterceptor());
    }
}
