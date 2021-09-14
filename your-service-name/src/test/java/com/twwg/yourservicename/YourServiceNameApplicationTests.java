package com.twwg.yourservicename;

import com.twwg.yourservicename.dao.UserDao;
import com.twwg.yourservicename.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = YourServiceNameApplication.class)
@Slf4j
class YourServiceNameApplicationTests {

    @Resource
    UserDao userDao;

    @Test
    void contextLoads() {
        int insert = userDao.insert(new User());

        log.info(String.valueOf(insert));
    }

}
