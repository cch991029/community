## 以撒社区

## 部署
### 依赖
- Git
- JDK
- Maven
- MySQL
## 步骤
- yum update
- yum install git
- mkdir App
- cd App
- git clone https://github.com/cch991029/community.git
- yum install maven
- mvn -v
- mvn clean compile package
- cp src/main/resources/application.properties src/main/resources/application-production.properties
- vim src/main/resources/application-production.properties
- mvn package
- java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar  到这里就结束了
因为回调地址的原因，所以进行以下修改
- ps -aux | grep java 检查当前进程是否存在
- git pull
- mvn package

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
[Spring Dev Tool](https://docs.spring.io/spring-boot/docs/2.1.11.RELEASE/reference/html/using-boot-devtools.html)  
[Spring MVC](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)  
[MyBatis Generator](http://mybatis.org/generator/running/runningWithMaven.html)  
[Markdown 插件](https://pandao.github.io/editor.md/)

## 工具
[Git](https://git-scm.com/download)  
[Visual Paradigm](https://www.visual-paradigm.com/)  
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)  
[Lombok](https://projectlombok.org/)  
[H2数据库](http://www.h2database.com/html/main.html)  
[Jquery](https://jquery.com/download/)  
### 浏览器插件
- One Tab
- Octotree

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
使用Flyway管理数据库：mvn flyway:migrate
使用逆向工程：mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
