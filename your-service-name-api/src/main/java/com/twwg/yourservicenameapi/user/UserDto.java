package com.twwg.yourservicenameapi.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户dto
 *
 * @author dragon
 * @date 2021/09/01
 */
@Data
@ApiModel("用户")
public class UserDto {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户密码")
    private String password;
}
