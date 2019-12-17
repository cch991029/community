## 以撒社区

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[es](https://elasticsearch.cn/explore)  
[GitHub deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)  
[Bootstrap](https://v3.bootcss.com/getting-started/)  
[GitHub Oauth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[Spring Boot](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/spring-boot-features.html#boot-features-sql)  
[MyBatis-Spring-Boot-Starter](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[OkHttp](https://square.github.io/okhttp/#get-a-url)  
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)   


## 工具
[Git](https://git-scm.com/download)  
[Visual Paradigm](https://www.visual-paradigm.com/)  
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)  
[Lombok](https://projectlombok.org/)  
[H2数据库](http://www.h2database.com/html/main.html)  

## 脚本
```sql
create table USER
(
    ID           int auto_increment primary key not null,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT
);
```
```bash
mvn flyway:migrate
```