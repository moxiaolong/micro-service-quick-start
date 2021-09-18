package com.twwg.api.yourservicename;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户feign
 * path是路径前缀，应该等于server.servlet.context-path
 *
 * @author dragon
 * @date 2021/09/01
 */

@FeignClient(name = "your-service-name", path = "your-service-name")
public interface UserFeign extends UserApi {
}

