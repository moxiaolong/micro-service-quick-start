package com.twwg.yourservicename.controller;

import com.twwg.yourservicename.config.ConfigManager;
import com.twwg.yourservicenameapi.Response;
import com.twwg.yourservicenameapi.user.UserDto;
import com.twwg.yourservicenameapi.user.UserFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户controller
 * <p>
 * 类和方法都应该有注释（如果是接口或继承可以只在上级注释），
 * 可以使用 idea 插件 easy javadoc 快速生成
 * 请尽可能多的书写注释，在维护代码时不要忘记维护注释
 *
 * @author dragon
 * @date 2021/09/01
 */
@RestController
public class UserController implements UserFeign {

    @Resource
    ConfigManager configManager;

    @Override
    public Response<UserDto> get(String name) {
        UserDto userDto = new UserDto();
        userDto.setName(name);

        return new Response<>(userDto);
    }

    @Override
    public Response<UserDto> post(UserDto userDto) {
        return new Response<>(userDto);
    }

    @GetMapping("/config")
    public Response<String> getConfig() {
        return new Response<String>().setData(configManager.getTest());
    }
}
