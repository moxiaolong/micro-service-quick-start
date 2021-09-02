package com.twwg.yourservicename.entity;

import lombok.Data;

/**
 * 用户
 * mybatis可以不书写xml约束,按照驼峰命名自动映射,如非必要请不要使用xml
 *
 * @author dragon
 * @date 2021/09/01
 */
@Data
public class User {
    private Long id;
}
