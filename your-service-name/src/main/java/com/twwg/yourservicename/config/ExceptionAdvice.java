package com.twwg.yourservicename.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twwg.api.yourservicename.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.twwg.yourservicename.config.TraceIDConfig.TRACE_ID_FLAG;

/**
 * 异常增强
 * 非生产环境向前端返回异常详细信息
 *
 * @author dragon
 * @date 2021/09/02
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @Value("${spring.profiles.active}")
    private String active;

    private static final String PROD = "prod";

    @Resource
    ObjectMapper objectMapper;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Object> onException(Exception exception,
                                        HttpServletRequest request) {
        exception.printStackTrace();
        if (log.isErrorEnabled()) {
            String requestUri = request.getRequestURI();
            Map<String, String[]> parameterMap = request.getParameterMap();
            String requestBody = null;
            if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
                try {
                    requestBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8.name());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                log.error("请求地址{},请求参数:{},异常信息:{}请求体:{},", requestUri,
                        objectMapper.writeValueAsString(parameterMap),
                        exception.getMessage(), requestBody);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }


        Response<Object> response = new Response<>().setCode(500);
        //生产环境隐藏异常信息
        if (PROD.equals(active)) {
            return response.setMessage("服务器异常");
        }
        return response.setMessage(exception.getMessage());
    }

}
