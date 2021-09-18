package com.twwg.common.core;

import com.twwg.common.config.FeignConfig;
import com.twwg.common.config.TraceIDConfig;
import com.twwg.common.config.ExceptionAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 启用微服务配置
 * 包括统一异常处理,traceId,Feign增强
 *
 * @author dragon
 * @date 2021/09/18
 */
@Configuration
@Import({TraceIDConfig.class, FeignConfig.class, ExceptionAdvice.class})
public @interface EnableMicroServiceCommonConfig {
}
