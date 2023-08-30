package com.goofy.ral.service

import com.goofy.ral.dto.TestCacheModel
import com.goofy.ral.dto.TestRequest
import com.goofy.ral.dto.TestResponse
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class TestV2Service(
    private val testV2AsyncService: TestV2AsyncService
) {
    fun getTestV2(request: TestRequest): TestResponse {
        /** Database */
        val test1ModelCf = testV2AsyncService.asyncFindAllByIdTest1(request.test1Id)
        val test2ModelCf = testV2AsyncService.asyncFindAllByIdTest2(request.test2Id)
        val test3ModelCf = testV2AsyncService.asyncFindAllByIdTest3(request.test3Id)
        val test4ModelCf = testV2AsyncService.asyncFindAllByIdTest4(request.test4Id)

        /** Redis Cache */
        val test1CacheCf = testV2AsyncService.asyncGetCache("test1:key:${request.test1Id}")
        val test2CacheCf = testV2AsyncService.asyncGetCache("test2:key:${request.test2Id}")
        val test3CacheCf = testV2AsyncService.asyncGetCache("test3:key:${request.test3Id}")
        val test4CacheCf = testV2AsyncService.asyncGetCache("test4:key:${request.test4Id}")

        /** Cpu Logic */
        val result1Cf = testV2AsyncService.asyncMathEngine()
        val result2Cf = testV2AsyncService.asyncMathEngine()
        val result3Cf = testV2AsyncService.asyncMathEngine()
        val result4Cf = testV2AsyncService.asyncMathEngine()

        /** WebClient Api Call */
        val realTrend1Cf = testV2AsyncService.asyncGetRealTrend()
        val realTrend2Cf = testV2AsyncService.asyncGetRealTrend()
        val realTrend3Cf = testV2AsyncService.asyncGetRealTrend()
        val realTrend4Cf = testV2AsyncService.asyncGetRealTrend()

        /** join all completable futures */
        CompletableFuture.allOf(
            test1ModelCf, test2ModelCf, test3ModelCf, test4ModelCf,
            test1CacheCf, test2CacheCf, test3CacheCf, test4CacheCf,
            result1Cf, result2Cf, result3Cf, result4Cf,
            realTrend1Cf, realTrend2Cf, realTrend3Cf, realTrend4Cf
        ).join()

        return TestResponse.of(
            cacheModel = TestCacheModel(
                test1 = test1CacheCf.get(),
                test2 = test2CacheCf.get(),
                test3 = test3CacheCf.get(),
                test4 = test4CacheCf.get()
            ),
            test1s = test1ModelCf.get(),
            test2s = test2ModelCf.get(),
            test3s = test3ModelCf.get(),
            test4s = test4ModelCf.get(),
            result = listOf(result1Cf.get(), result2Cf.get(), result3Cf.get(), result4Cf.get()),
            trendModels = listOf(realTrend1Cf.get(), realTrend2Cf.get(), realTrend3Cf.get(), realTrend4Cf.get())
        )
    }
}
