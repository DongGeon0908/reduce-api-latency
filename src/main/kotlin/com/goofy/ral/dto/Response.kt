package com.goofy.ral.dto

import com.goofy.ral.client.google.model.GoogleRealTimeSearchTrendModel
import com.goofy.ral.entity.Test1
import com.goofy.ral.entity.Test2
import com.goofy.ral.entity.Test3
import com.goofy.ral.entity.Test4
import java.time.ZonedDateTime

data class TestResponse(
    val cacheModel: TestCacheModel?,
    val test1: List<Test1Model>,
    val test2: List<Test2Model>,
    val test3: List<Test3Model>,
    val test4: List<Test4Model>,
    val engineResult: List<String>,
    val trendModels: List<GoogleRealTimeSearchTrendModel>
) {
    companion object {
        fun of(
            cacheModel: TestCacheModel?,
            test1s: List<Test1Model>,
            test2s: List<Test2Model>,
            test3s: List<Test3Model>,
            test4s: List<Test4Model>,
            result: List<String>,
            trendModels: List<GoogleRealTimeSearchTrendModel>
        ): TestResponse {
            return TestResponse(cacheModel, test1s, test2s, test3s, test4s, result, trendModels)
        }
    }
}

data class TestCacheModel(
    val test1: String?,
    val test2: String?,
    val test3: String?,
    val test4: String?,
)

data class Test1Model(
    val id: Long,
    val title: String?,
    val description: String?,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime
) {
    companion object {
        fun from(test1: Test1): Test1Model {
            return Test1Model(
                id = test1.id,
                title = test1.title,
                description = test1.description,
                createdAt = test1.createdAt,
                modifiedAt = test1.modifiedAt
            )
        }
    }
}

data class Test2Model(
    val id: Long,
    val title: String?,
    val description: String?,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime
) {
    companion object {
        fun from(test2: Test2): Test2Model {
            return Test2Model(
                id = test2.id,
                title = test2.title,
                description = test2.description,
                createdAt = test2.createdAt,
                modifiedAt = test2.modifiedAt
            )
        }
    }
}

data class Test3Model(
    val id: Long,
    val title: String?,
    val description: String?,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime
) {
    companion object {
        fun from(test3: Test3): Test3Model {
            return Test3Model(
                id = test3.id,
                title = test3.title,
                description = test3.description,
                createdAt = test3.createdAt,
                modifiedAt = test3.modifiedAt
            )
        }
    }
}

data class Test4Model(
    val id: Long,
    val title: String?,
    val description: String?,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime
) {
    companion object {
        fun from(test4: Test4): Test4Model {
            return Test4Model(
                id = test4.id,
                title = test4.title,
                description = test4.description,
                createdAt = test4.createdAt,
                modifiedAt = test4.modifiedAt
            )
        }
    }
}
