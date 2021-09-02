package com.twwg.yourservicename.config;

import com.twwg.yourservicenameapi.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常增强
 * 非生产环境向前端返回异常详细信息
 *
 * @author dragon
 * @date 2021/09/02
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @Value("${spring.profiles.active}")
    private String active;

    private static final String PROD = "prod";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Object> onException(Exception exception) {
        Response<Object> response = new Response<>().setCode(500);
        if (PROD.equals(active)) {
            return response.setMessage("服务器异常");
        }
        return response.setMessage(exception.getMessage());
    }

}
