package com.twwg.api.yourservicename.user;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户feign
 *
 * @author dragon
 * @date 2021/09/01
 */

@FeignClient(name = "your-service-name")
public interface UserFeign extends UserApi {
}

