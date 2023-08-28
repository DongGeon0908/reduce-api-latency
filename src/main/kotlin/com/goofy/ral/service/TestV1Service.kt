package com.goofy.ral.service

import com.goofy.ral.client.google.GoogleClient
import com.goofy.ral.dto.Test1Model
import com.goofy.ral.dto.Test2Model
import com.goofy.ral.dto.Test3Model
import com.goofy.ral.dto.Test4Model
import com.goofy.ral.dto.TestCacheModel
import com.goofy.ral.dto.TestRequest
import com.goofy.ral.dto.TestResponse
import com.goofy.ral.repository.Test1Repository
import com.goofy.ral.repository.Test2Repository
import com.goofy.ral.repository.Test3Repository
import com.goofy.ral.repository.Test4Repository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class TestV1Service(
    private val test1Repository: Test1Repository,
    private val test2Repository: Test2Repository,
    private val test3Repository: Test3Repository,
    private val test4Repository: Test4Repository,
    private val cacheService: CacheService,
    private val mathEngine: MathEngine,
    private val googleClient: GoogleClient
) {
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
}
