package com.goofy.ral.service

import com.goofy.ral.client.google.GoogleClient
import com.goofy.ral.client.google.model.GoogleRealTimeSearchTrendModel
import com.goofy.ral.dto.Test1Model
import com.goofy.ral.dto.Test2Model
import com.goofy.ral.dto.Test3Model
import com.goofy.ral.dto.Test4Model
import com.goofy.ral.repository.Test1Repository
import com.goofy.ral.repository.Test2Repository
import com.goofy.ral.repository.Test3Repository
import com.goofy.ral.repository.Test4Repository
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class TestV2AsyncService(
    private val test1Repository: Test1Repository,
    private val test2Repository: Test2Repository,
    private val test3Repository: Test3Repository,
    private val test4Repository: Test4Repository,
    private val cacheService: CacheService,
    private val mathEngine: MathEngine,
    private val googleClient: GoogleClient
) {
    @Async(value = "taskExecutor")
    fun asyncFindAllByIdTest1(ids: Set<Long>): CompletableFuture<List<Test1Model>> {
        return CompletableFuture.completedFuture(
            test1Repository.findAllById(ids)
                .map { test1 -> Test1Model.from(test1) }
        )
    }

    @Async(value = "taskExecutor")
    fun asyncFindAllByIdTest2(ids: Set<Long>): CompletableFuture<List<Test2Model>> {
        return CompletableFuture.completedFuture(
            test2Repository.findAllById(ids)
                .map { test2 -> Test2Model.from(test2) }
        )
    }

    @Async(value = "taskExecutor")
    fun asyncFindAllByIdTest3(ids: Set<Long>): CompletableFuture<List<Test3Model>> {
        return CompletableFuture.completedFuture(
            test3Repository.findAllById(ids)
                .map { test3 -> Test3Model.from(test3) }
        )
    }

    @Async(value = "taskExecutor")
    fun asyncFindAllByIdTest4(ids: Set<Long>): CompletableFuture<List<Test4Model>> {
        return CompletableFuture.completedFuture(
            test4Repository.findAllById(ids)
                .map { test4 -> Test4Model.from(test4) }
        )
    }

    @Async(value = "taskExecutor")
    fun asyncMathEngine(): CompletableFuture<String> {
        return CompletableFuture.completedFuture(
            mathEngine.execute()
        )
    }

    @Async(value = "taskExecutor")
    fun asyncGetCache(key: String): CompletableFuture<String> {
        return CompletableFuture.completedFuture(
            cacheService.get(key)
        )
    }

    @Async(value = "taskExecutor")
    fun asyncGetRealTrend(): CompletableFuture<GoogleRealTimeSearchTrendModel> {
        return CompletableFuture.completedFuture(
            runBlocking { googleClient.getRealTimeTrends() }
        )
    }
}
