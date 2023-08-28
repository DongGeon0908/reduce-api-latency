package com.goofy.ral.client.google

import com.goofy.ral.config.WebClientFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotBlank

@Configuration
class GoogleClientConfig(
    private val googleClientProperties: GoogleClientProperties
) {
    @Bean
    fun googleClient(): GoogleClient {
        return WebClientFactory.generate(
            baseUrl = googleClientProperties.dailyTrendsUrl,
            connectionTimeoutMillis = 5000,
            readTimeoutMillis = 5000,
            writeTimeoutMillis = 5000
        ).run { SuspendableGoogleClient(this) }
    }
}

@Configuration
@ConfigurationProperties(prefix = "client.search-trend.google")
@ConfigurationPropertiesBinding
data class GoogleClientProperties(
    @field:NotBlank
    var dailyTrendsUrl: String = ""
)
