package com.twwg.yourservicename.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 配置管理器
 * 从配置中心刷新 @RefreshScope
 *
 * @author dragon
 * @date 2021/09/02
 */
@Configuration
@RefreshScope
@Slf4j
public class ConfigManager {
    @Value("${test:default}")
    private String test;

    public String getTest() {
        return test;
    }

}
