package com.goofy.ral.extension

import com.goofy.ral.dto.PageResponseDto
import com.goofy.ral.dto.ResponseDto
import org.springframework.data.domain.Page

/**
 * Wrap Response Ok
 **/
fun <T> T.wrap() = ResponseDto(this)

/**
 * Wrap Response Page
 **/
fun <T> Page<T>.wrapPage() = PageResponseDto(this)
