package com.goofy.ral.repository

import com.goofy.ral.entity.Test4
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Test4Repository : JpaRepository<Test4, Long> {
}
