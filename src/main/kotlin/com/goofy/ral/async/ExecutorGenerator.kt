package com.goofy.ral.async

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

/**
 * 멀티스레드 프로그래밍을 위한 ExecutorGenerator
 * @property threadName thread name
 * @property corePoolSize 기본 실행 대기하는 Thread의 수
 * @property maxPoolSize 동시 동작하는 최대 Thread의 수
 * @property queueCapacity MaxPoolSize 초과 요청에서 Thread 생성 요청시, 해당 요청을 Queue에 저장하는데 이때 최대 수용 가능한 Queue의 수,Queue에 저장되어있다가 Thread에 자리가 생기면 하나씩 빠져나가 동작
 **/
class ExecutorGenerator(
    private val threadName: String,
    private val corePoolSize: Int = DEFAULT_EXECUTOR_CORE_POOL_SIZE,
    private val maxPoolSize: Int = DEFAULT_EXECUTOR_MAX_POOL_SIZE,
    private val queueCapacity: Int = DEFAULT_EXECUTOR_QUEUE_CAPACITY
) {
    private val logger = mu.KotlinLogging.logger {}

    companion object {
        const val DEFAULT_EXECUTOR_CORE_POOL_SIZE = 5
        const val DEFAULT_EXECUTOR_MAX_POOL_SIZE = 10
        const val DEFAULT_EXECUTOR_QUEUE_CAPACITY = 10
    }

    fun generate(): ThreadPoolTaskExecutor {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor()

        threadPoolTaskExecutor.corePoolSize = this.corePoolSize
        threadPoolTaskExecutor.maxPoolSize = this.maxPoolSize
        threadPoolTaskExecutor.queueCapacity = this.queueCapacity
        threadPoolTaskExecutor.setThreadNamePrefix("${this.threadName}-")
        threadPoolTaskExecutor.setTaskDecorator(AsyncTaskDecorator())
        threadPoolTaskExecutor.setRejectedExecutionHandler(AsyncRejectedExecutionHandler())
        threadPoolTaskExecutor.initialize()

        logger.info { "generate ThreadPoolTaskExecutor / threadName $threadName / corePoolSize $corePoolSize / maxPoolSize $maxPoolSize / queueCapacity $queueCapacity" }

        return threadPoolTaskExecutor
    }
}
