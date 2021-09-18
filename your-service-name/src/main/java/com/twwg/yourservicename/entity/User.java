package com.twwg.yourservicename.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户
 * mybatis可以不书写xml约束,按照驼峰命名自动映射,如非必要请不要使用xml
 *
 * @author dragon
 * @date 2021/09/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Model<User> {
    private Long id;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
