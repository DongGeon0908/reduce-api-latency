# Reduce Api Latency

> This repository offers resources for reducing latency through techniques, source code, and tutorials. Ideal for developers and engineers interested in optimizing network, computing, and data processing for real-world applications.

---

### Project Spec

- SpringBoot 2.7.6
- Mysql 8.0
- Redis
- Kotlin by Java-11

---

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
CREATE
DATABASE reduce_api_latency CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

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

---

### Basic Api Setup (Part.1)

> Based on the provided description, it appears that the API operates synchronously with 4 database queries, 4 Redis queries, and 4 CPU computation steps. Finally, it makes 4 calls to the Google Search Trend API. The average latency for these operations falls between 1.5 seconds to 2 seconds.

```kotlin
fun getTestV1(request: TestRequest): TestResponse {
    /** Database */
    val test1Model = test1Repository.findAllById(request.test1Id)
        .map { test1 -> Test1Model.from(test1) }
    val test2Model = test2Repository.findAllById(request.test2Id)
        .map { test2 -> Test2Model.from(test2) }
    val test3Model = test3Repository.findAllById(request.test3Id)
        .map { test3 -> Test3Model.from(test3) }
    val test4Model = test4Repository.findAllById(request.test4Id)
        .map { test4 -> Test4Model.from(test4) }

    /** Redis Cache */
    val test1Cache = cacheService.get("test1:key:${request.test1Id}")
    val test2Cache = cacheService.get("test2:key:${request.test2Id}")
    val test3Cache = cacheService.get("test3:key:${request.test3Id}")
    val test4Cache = cacheService.get("test4:key:${request.test4Id}")

    /** Cpu Logic */
    val result1 = mathEngine.execute()
    val result2 = mathEngine.execute()
    val result3 = mathEngine.execute()
    val result4 = mathEngine.execute()

    /** WebClient Api Call */
    val realTrend1 = runBlocking { googleClient.getRealTimeTrends() }
    val realTrend2 = runBlocking { googleClient.getRealTimeTrends() }
    val realTrend3 = runBlocking { googleClient.getRealTimeTrends() }
    val realTrend4 = runBlocking { googleClient.getRealTimeTrends() }

    return TestResponse.of(
        cacheModel = TestCacheModel(
            test1 = test1Cache,
            test2 = test2Cache,
            test3 = test3Cache,
            test4 = test4Cache
        ),
        test1s = test1Model,
        test2s = test2Model,
        test3s = test3Model,
        test4s = test4Model,
        result = listOf(result1, result2, result3, result4),
        trendModels = listOf(realTrend1, realTrend2, realTrend3, realTrend4)
    )
}
```

**Add coroutine dependencies to use runBlocking.**

```kotlin
const val COROUTINE_VERSION = "1.6.4"

/** coroutine */
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${DependencyVersion.COROUTINE_VERSION}")
implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
```

**Example API CURL**

```
curl --request GET \
  --url 'http://localhost:8080/api/ral/v1/test?test1Id=1&test1Id=2&test1Id=3&test1Id=4&test1Id=5&test1Id=6&test1Id=7&test2Id=1&test2Id=2&test2Id=3&test2Id=4&test2Id=5&test2Id=6&test2Id=7&test3Id=1&test3Id=2&test3Id=3&test3Id=4&test3Id=5&test3Id=6&test3Id=7&test4Id=1&test4Id=2&test4Id=3&test4Id=4&test4Id=5&test4Id=6&test4Id=7'
```
