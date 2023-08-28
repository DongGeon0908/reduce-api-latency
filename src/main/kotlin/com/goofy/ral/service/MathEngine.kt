package com.goofy.ral.service

import org.springframework.stereotype.Component

@Component
class MathEngine {
    /** this functions takes approximately 50ms. */
    fun execute(): String {
        (0..25_000_000).forEach { val a = it }

        return "Math Engine Finish"
    }
}
