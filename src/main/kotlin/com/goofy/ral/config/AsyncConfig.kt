package com.goofy.ral.config

import com.goofy.ral.async.ExecutorGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@EnableAsync
@Configuration
class AsyncConfig : AsyncConfigurerSupport() {
    @Bean("taskExecutor")
    fun taskExecutor(): ThreadPoolTaskExecutor {
        return ExecutorGenerator(
            threadName = "taskExecutor",
            corePoolSize = 10,
            maxPoolSize = 20,
            queueCapacity = 20
        ).generate()
    }
}
