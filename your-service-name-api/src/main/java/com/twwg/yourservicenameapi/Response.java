package com.twwg.yourservicenameapi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * 响应
 *
 * @author dragon
 * @date 2021/09/01
 */
@Data
@Accessors(chain = true)
@ApiModel("响应")
public class Response<T> {

    @ApiModelProperty("响应状态，200：成功")
    private Integer code = HttpStatus.OK.value();
    @ApiModelProperty("提示消息")
    private String message = "success";
    @ApiModelProperty("携带数据")
    private T data;


    public Response() {
    }

    /**
     * 响应
     *
     * @param data 数据
     */
    public Response(T data) {
        this.data = data;
    }
}
