package com.goofy.ral.dto

import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort

data class ResponseDto<T>(val data: T)

data class PageResponseDto<T>(
    val data: List<T>,
    val page: Int?,
    val size: Int?,
    val totalPage: Int,
    val totalCount: Long,
    val sort: Sort
) {
    constructor(page: Page<T>) : this(
        data = page.content,
        page = page.pageable.pageNumber,
        size = page.size,
        totalPage = page.totalPages,
        totalCount = page.totalElements,
        sort = page.sort
    )
}
