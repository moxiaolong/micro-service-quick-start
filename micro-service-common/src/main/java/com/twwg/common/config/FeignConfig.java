package com.twwg.common.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twwg.common.exception.FeignException;
import feign.Feign;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Feign 配置
 * <p>
 * 解决WebMVC因为Feign类上包含@RequestMapping将其视为Controoler的问题
 *
 * @author dragon
 * @date 2021/09/14
 */
@Configuration
@ConditionalOnClass({Feign.class})
@Slf4j
public class FeignConfig implements WebMvcRegistrations {


    @Resource
    ObjectMapper objectMapper;

    private final RequestMappingHandlerMapping requestMappingHandlerMapping = new FeignRequestMappingHandlerMapping();

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return requestMappingHandlerMapping;
    }


    /**
     * 过滤Controller生成
     */
    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }

    /**
     * 把Feign的异常抛出
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            String json = null;
            String message = null;
            try {
                json = Util.toString(response.body().asReader(Charset.defaultCharset()));
                Map<String, String> map = objectMapper.readValue(json, new TypeReference<>() {
                });
                message = methodKey + "::" + map.get("message");

            } catch (IOException e) {
                e.printStackTrace();
                return new FeignException("Feign调用异常，异常信息解析失败");
            }
            return new FeignException(message);
        };
    }


}