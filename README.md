微服务快速启动,含Flyway,Mybatis plus,Feign,Nacos。

使用时需要正确配置数据库、Nacos地址。

使用JDK11。

## Controller 与 Feign

Feign中的name要和本项目配置name一致。 需要暴露的服务写在Feign中，Controller实现Feign接口，可以继承Feign上的webMVC注解和Swagger注解 为了简洁可优先在Feign上书写注解。

## 自带页面

Druid面板地址：/druid/login.html Swagger地址：/document.html

* 网关应该屏蔽这两个地址的暴露

## TraceID说明

当一个请求进来时会获取请求头的traceID，如果没有会生成一个，由此请求衍生的日志都会协带traceID， 在Feign调用其他服务时会传递traceID，在响应时也会在响应头放置。

## 异常处理说明

在非生产环境中将返回详细地异常信息和traceID。 如果需要使用全局异常处理，请将异常抛到Controller外， 在出现异常时将会打印请求地址，参数，请求体信息，以便追查问题。

## 日志查看说明

日志存放位置./logs/web-error-%d{yyyy-MM-dd}.%i.log 保留10天，100M分页。 不同级别日志将分开保存。 日志查看接口/log/list ; /log 请求示例:
/log?fileName=web_info.log&content=ERROR

## 配置文件

运行环境默认local,选择其他环境请通过JVM传参的方式修改,不要修改配置文件。

## 领域驱动设计

本项目遵循领域驱动设计（DDD）,可不严格遵守MVC架构,如无事务控制需要,可越过Service直接使用DAO。

如非预料到大概率更换Service实现的可能，Service可以不使用接口。

## 数据库

请尽量避免使用SQL写业务，避免使用连表、内查询。 使用MybatisPlus,尽量避免使用XML文件。

## 日期和时间

明确区分日期和时间类型，禁止使用Date类型，

序列化格式 localDate:yyyy-MM-dd,LocalDateTime:yyyy-MM-ddTHH:mm:ss.S。