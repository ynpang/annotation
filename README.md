# Getting Started
，
### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Spring Boot 2.1.6] (https://spring.io/projects/spring-boot/)

# 什么是注解
注解（annotation）:元数据，一种代码级别的说明：简单的说，注解如同一张标签。
* [解读一：注解是一个携带数据的附属品；)
* [解读二：在特定的场景由外部解析产生作用；)

# 通过代码了解注解的本质
关于注解的定义-元注解
@Document-注解是否将包含在JavaDoc中
@Retention-什么时候使用该注解 可选值为：source、class、runtime
@Target-注解用于什么地方
@Inherited-是否允许子类继承该注解

# 关于注解的解析
要获取类方法和字段的注解信息，必须通过Java的反射技术来获取Annotaion对象，因此你除此之外没有别的获取注解对象的方法；

1.创建自定义注解 2.将注解加在需要简化的方法上 3.编写切面类 4.运行代码进行验证
<aop:aspectj-autoproxy proxy-target-class="true" />

