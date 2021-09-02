package com.twwg.yourservicename.config;

import com.alibaba.nacos.common.util.UuidUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跟踪 id Config
 *
 * @author dragon
 * @date 2021/09/02
 */
@Configuration
public class TraceIDConfig implements HandlerInterceptor, WebMvcConfigurer, RequestInterceptor {
     static final String TRACE_ID_FLAG = "traceId";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(TRACE_ID_FLAG);
        if (StringUtils.isEmpty(traceId)) {
            traceId = UuidUtils.generateUuid();
        }
        MDC.put(TraceIDConfig.TRACE_ID_FLAG, traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
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

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(TRACE_ID_FLAG, MDC.get(TRACE_ID_FLAG));
    }
}
