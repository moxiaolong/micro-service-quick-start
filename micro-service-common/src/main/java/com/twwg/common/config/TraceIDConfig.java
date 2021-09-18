package com.twwg.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 跟踪 id Config
 *
 * @author dragon
 * @date 2021/09/02
 */
@Configuration
@Slf4j
public class TraceIDConfig implements HandlerInterceptor, WebMvcConfigurer, RequestInterceptor {
    static final String TRACE_ID_FLAG = "traceId";


    /**
     * 从请求头获取trace Id 如果没有就生成
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = request.getHeader(TRACE_ID_FLAG);
        if (StringUtils.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put(TraceIDConfig.TRACE_ID_FLAG, traceId);
        return true;
    }

    /**
     * 响应头携带trace Id
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String traceId = MDC.get(TRACE_ID_FLAG);
        if (traceId != null) {
            response.setHeader(TRACE_ID_FLAG, traceId);
            MDC.remove(TRACE_ID_FLAG);
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
    }

    /**
     * Feign调用时在请求头携带当前trace Id
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(TRACE_ID_FLAG, MDC.get(TRACE_ID_FLAG));
    }
}
