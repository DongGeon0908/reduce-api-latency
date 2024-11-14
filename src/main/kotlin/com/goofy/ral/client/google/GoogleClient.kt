package com.goofy.ral.client.google

import com.goofy.ral.client.google.model.GoogleRealTimeSearchTrendModel

interface GoogleClient {
    /** 실시간 검색 트랜드 */
    suspend fun getRealTimeTrends(): GoogleRealTimeSearchTrendModel?
}
