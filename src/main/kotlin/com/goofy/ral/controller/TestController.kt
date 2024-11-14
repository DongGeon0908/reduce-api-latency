package com.goofy.ral.controller

import com.goofy.ral.dto.TestRequest
import com.goofy.ral.extension.wrap
import com.goofy.ral.service.TestV1Service
import com.goofy.ral.service.TestV2Service
import com.goofy.ral.service.TestV3Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testV1Service: TestV1Service,
    private val testV2Service: TestV2Service,
    private val testV3Service: TestV3Service,
) {
    /** default API */
    @GetMapping("/api/ral/v1/test")
    fun getTestV1(
        request: TestRequest
    ) = testV1Service.getTestV1(request).wrap()

    /** with CompletableFuture and Async */
    @GetMapping("/api/ral/v2/test")
    fun getTestV2(
        request: TestRequest
    ) = testV2Service.getTestV2(request).wrap()

    /** with Coroutines */
    @GetMapping("/api/ral/v3/test")
    suspend fun getTestV3(
        request: TestRequest
    ) = testV3Service.getTestV3(request).wrap()
}
