package com.goofy.ral.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class CacheService(
    private val stringRedisTemplate: StringRedisTemplate
) {
    fun get(key: String): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }
}
