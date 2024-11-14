package com.goofy.ral.client.google

import com.fasterxml.jackson.module.kotlin.readValue
import com.goofy.ral.client.google.model.GoogleRealTimeSearchTrendModel
import com.goofy.ral.extension.mapper
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class SuspendableGoogleClient(
    private val webClient: WebClient
) : GoogleClient {
    private val logger = mu.KotlinLogging.logger {}

    override suspend fun getRealTimeTrends(): GoogleRealTimeSearchTrendModel? {
        return webClient.get()
            .uri("/trends/api/realtimetrends") { builder ->
                builder
                    .queryParam("hl", "ko")
                    .queryParam("tz", -540)
                    .queryParam("cat", "all")
                    .queryParam("fi", 0)
                    .queryParam("fs", 0)
                    .queryParam("geo", "US")
                    .queryParam("ri", 300)
                    .queryParam("rs", 20)
                    .queryParam("sort", 0)
                    .build()
            }
            .retrieve()
            .awaitBody<String>().trimIndent().substringAfter("\n").run {
                mapperGoogleResponseModel(this)
            }
    }

    private inline fun <reified T> mapperGoogleResponseModel(response: String): T? {
        return runCatching {
            mapper.readValue<T>(response)
        }.getOrNull()
    }
}
