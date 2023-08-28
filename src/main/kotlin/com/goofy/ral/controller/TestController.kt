package com.goofy.ral.controller

import com.goofy.ral.service.TestService
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testService: TestService
) {

}
