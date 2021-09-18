微服务快速启动,含Flyway,Mybatis plus,Feign,Nacos。

使用时需要正确配置数据库、Nacos地址。

使用JDK11。

# 快速开始

准备一个nacos环境并修改配置一致，例如 docker run -d -p 8848:8848 --env MODE=standalone --name nacos nacos/nacos-server。

准备一个mysql环境并修改配置一致，创建test库。 例如 docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql。

启动项目。

访问 GET localhost:8080/your-user-name/user

# 详细功能

## Controller 与 Feign

Feign中的name要和本项目配置name一致。

需要暴露的服务写在Api中，Controller实现Api接口，Feign继承Api，这样做是为了让Feign和Controller共享WebMVC注解和Swagger注解,避免同时维护两份导致不一致。

## 自带页面

Druid面板地址：/druid/login.html Swagger地址：/document.html

* 网关应该屏蔽这两个地址的暴露

## TraceID说明

当一个请求进来时会获取请求头的traceID，如果没有会生成一个，由此请求衍生的日志都会协带traceID， 在Feign调用其他服务时会传递traceID，在响应时也会在响应头放置。

## 异常处理说明

在非生产环境中将返回详细地异常信息和traceID。 如果需要使用全局异常处理，请将异常抛到Controller外， 在出现异常时将会打印请求地址，参数，请求体信息，以便追查问题。

## 日志查看说明

日志存放位置./logs/web-error-%d{yyyy-MM-dd}.%i.log 保留15天，100M分页。

不同级别日志将分开保存。

日志查看接口/log/list ; /log 请求示例:

/log?fileName=web_info.log&content=ERROR

## 配置文件

运行环境默认local,选择其他环境请通过JVM传参的方式修改,不要修改配置文件。

## 领域驱动设计

**本项目推荐遵循领域驱动设计（DDD）,可不严格遵守MVC架构,DDD的主要目的是为了避免同层调用,增加可维护性,你可以自己选择分层方式,但要避免同层之间存在依赖调用,所有的调用都只能是自上向下的。**

例如：你的服务分成了ABC三层，那么允许的调用方式是A-B-C,当C层的一种数据C1需要和C层的另一种数据C2结合时，禁止出现C1-C2的调用，应该由B层调用C1+C2，向上类推，

当你发现需复用A层A1逻辑和A2逻辑组合时，就会发现三层架构不能满足你，就该考虑增加层数了，所以你应该结合业务复杂度，提前考虑层数。

供参考的三层架构包命名为controller-service-dao。 供参考的四层架构包命名是controller-service-domain-dao。

如非预料到大概率更换Service实现的可能，Service可以不使用接口。

## 数据库

请尽量避免使用SQL写业务，避免使用连表、内查询。 使用MybatisPlus,尽量避免使用XML文件。

## 日期和时间

明确区分日期和时间类型，禁止使用Date类型，

序列化格式 localDate:yyyy-MM-dd,LocalDateTime:yyyy-MM-ddTHH:mm:ss.S。