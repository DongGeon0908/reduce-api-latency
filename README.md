# Reduce Api Latency

> This repository offers resources for reducing latency through techniques, source code, and tutorials. Ideal for developers and engineers interested in optimizing network, computing, and data processing for real-world applications.

### Setup Project (Part.0)

**Dependency**
```groovy
object DependencyVersion {
    const val KOTLIN_LOGGING_VERSION = "3.0.0"
    const val LOGBACK_ENCODER = "7.2"
}

dependencies {
    /** spring starter */
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    /** kotlin */
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    /** logger */
    implementation("io.github.microutils:kotlin-logging-jvm:${DependencyVersion.KOTLIN_LOGGING_VERSION}")
    implementation("net.logstash.logback:logstash-logback-encoder:${DependencyVersion.LOGBACK_ENCODER}")

    /** mysql */
    runtimeOnly("mysql:mysql-connector-java")

    /** etc */
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}
```

**DDL**

```sql
-- CREATE TABLE
CREATE DATABASE reduce_api_latency CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `test_1`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `createdAt`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `modifiedAt`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `test_2`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `createdAt`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `modifiedAt`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `test_3`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `createdAt`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `modifiedAt`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `test_4`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `createdAt`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `modifiedAt`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

```

**application.yml**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/reduce_api_latency?useUnicode=true&charset=utf8mb4&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password:
    hikari:
      minimum-idle: 10
      maximum-pool-size: 20
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: none
    open-in-view: false
  redis:
    host: localhost
    port: 6379

```
