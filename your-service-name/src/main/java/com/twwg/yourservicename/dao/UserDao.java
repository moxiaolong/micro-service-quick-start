package com.twwg.yourservicename.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twwg.yourservicename.entity.User;

/**
 * 书写sql时应避免使用长sql，尽可能避免连表和内查询，用业务代码实现而非SQL语句。
 *
 * @author dragon
 * @date 2021/09/01
 */
public interface UserDao extends BaseMapper<User> {
}
