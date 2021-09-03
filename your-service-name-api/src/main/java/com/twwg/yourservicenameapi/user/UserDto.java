package com.twwg.yourservicenameapi.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户dto
 *
 * @author dragon
 * @date 2021/09/01
 */
@Data
@Accessors(chain = true)
@ApiModel("用户")
public class UserDto {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("生日（日期）")
    private LocalDate birthday;
    @ApiModelProperty("创建时间")
    private LocalDateTime creatTime;
}
