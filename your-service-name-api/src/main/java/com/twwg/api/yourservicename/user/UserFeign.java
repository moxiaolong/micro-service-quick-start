package com.twwg.api.yourservicename.user;

import com.twwg.api.yourservicename.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


/**
 * 用户api
 *
 * @author dragon
 * @date 2021/09/01
 */
@FeignClient(name = "your-service-name")
@RequestMapping("/user")
@Api
public interface UserFeign {

    /**
     * get
     *
     * @param name 的名字
     * @return {@link Response}<{@link UserDto}>
     */
    @GetMapping
    @ApiOperation("获取一个用户")
    Response<UserDto> get(@Nullable @RequestParam @ApiParam("用户名参数") String name);

    /**
     * post
     *
     * @param userDto 用户dto
     * @return {@link Response}<{@link UserDto}>
     */
    @PostMapping
    @ApiOperation("添加一个用户")
    Response<UserDto> post(@RequestBody UserDto userDto);

    /**
     * 调用时将会产生一个异常
     *
     * @return {@link Response}<{@link Integer}>
     */
    @RequestMapping("/error")
    Response<Integer> error();
}
