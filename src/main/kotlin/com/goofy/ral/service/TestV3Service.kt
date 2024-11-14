package com.goofy.ral.service

import com.goofy.ral.client.google.GoogleClient
import com.goofy.ral.dto.*
import com.goofy.ral.repository.Test1Repository
import com.goofy.ral.repository.Test2Repository
import com.goofy.ral.repository.Test3Repository
import com.goofy.ral.repository.Test4Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class TestV3Service(
    private val test1Repository: Test1Repository,
    private val test2Repository: Test2Repository,
    private val test3Repository: Test3Repository,
    private val test4Repository: Test4Repository,
    private val cacheService: CacheService,
    private val mathEngine: MathEngine,
    private val googleClient: GoogleClient,
) {
    suspend fun getTestV3(request: TestRequest): TestResponse {
        return coroutineScope {
            /** Database */
            val test1Model = async(Dispatchers.IO) {
                test1Repository.findAllById(request.test1Id).map { test1 -> Test1Model.from(test1) }
            }
            val test2Model = async(Dispatchers.IO) {
                test2Repository.findAllById(request.test2Id).map { test2 -> Test2Model.from(test2) }
            }
            val test3Model = async(Dispatchers.IO) {
                test3Repository.findAllById(request.test3Id).map { test3 -> Test3Model.from(test3) }
            }
            val test4Model = async(Dispatchers.IO) {
                test4Repository.findAllById(request.test4Id).map { test4 -> Test4Model.from(test4) }
            }

            /** Redis Cache */
            val test1Cache = async(Dispatchers.IO) {
                cacheService.get("test1:key:${request.test1Id}")
            }
            val test2Cache = async(Dispatchers.IO) {
                cacheService.get("test2:key:${request.test2Id}")
            }
            val test3Cache = async(Dispatchers.IO) {
                cacheService.get("test3:key:${request.test3Id}")
            }
            val test4Cache = async(Dispatchers.IO) {
                cacheService.get("test4:key:${request.test4Id}")
            }

            /** Cpu Logic */
            val result1 = async(Dispatchers.Default) {
                mathEngine.execute()
            }
            val result2 = async(Dispatchers.Default) {
                mathEngine.execute()
            }
            val result3 = async(Dispatchers.Default) {
                mathEngine.execute()
            }
            val result4 = async(Dispatchers.Default) {
                mathEngine.execute()
            }

            /** WebClient Api Call */
            val realTrend1 = async(Dispatchers.IO) { googleClient.getRealTimeTrends() }
            val realTrend2 = async(Dispatchers.IO) { googleClient.getRealTimeTrends() }
            val realTrend3 = async(Dispatchers.IO) { googleClient.getRealTimeTrends() }
            val realTrend4 = async(Dispatchers.IO) { googleClient.getRealTimeTrends() }

            TestResponse.of(
                cacheModel = TestCacheModel(
                    test1 = test1Cache.await(),
                    test2 = test2Cache.await(),
                    test3 = test3Cache.await(),
                    test4 = test4Cache.await()
                ),
                test1s = test1Model.await(),
                test2s = test2Model.await(),
                test3s = test3Model.await(),
                test4s = test4Model.await(),
                result = listOf(result1.await(), result2.await(), result3.await(), result4.await()),
                trendModels = listOfNotNull(
                    realTrend1.await(),
                    realTrend2.await(),
                    realTrend3.await(),
                    realTrend4.await()
                )
            )
        }
    }
}
