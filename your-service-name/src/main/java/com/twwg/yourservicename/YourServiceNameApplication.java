package com.twwg.yourservicename;

import com.mg.swagger.framework.configuration.EnableSwaggerMgUi;
import com.twwg.common.core.EnableMicroServiceCommonConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 应用程序
 * swagger地址： document.html
 * 德鲁伊地址 /druid/login.html
 * feign包应该遵循命名规范 com.twwg.api.service-name
 *
 * @author dragon
 * @date 2021/09/01
 */
@SpringBootApplication
@EnableSwagger2
@EnableSwaggerMgUi
@EnableFeignClients("com.twwg.api.*")
@MapperScan(basePackages = "com.twwg.*.dao")
@EnableDiscoveryClient
public class YourServiceNameApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourServiceNameApplication.class, args);
    }

}


