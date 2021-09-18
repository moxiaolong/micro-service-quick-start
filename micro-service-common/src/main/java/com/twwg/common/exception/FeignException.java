package com.twwg.common.exception;

/**
 * feign服务器异常
 *
 * @author dragon
 * @date 2021/09/18
 */
public class FeignException extends RuntimeException{
    public FeignException(String message) {
        super(message);
    }
}
