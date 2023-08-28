package com.goofy.ral.controller

import com.goofy.ral.dto.TestRequest
import com.goofy.ral.extension.wrap
import com.goofy.ral.service.TestV1Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testV1Service: TestV1Service
) {
    @GetMapping("/api/ral/v1/test")
    fun getTestV1(
        request: TestRequest
    ) = testV1Service.getTestV1(request).wrap()
}
