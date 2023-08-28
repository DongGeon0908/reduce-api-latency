package com.goofy.ral.repository

import com.goofy.ral.entity.Test3
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Test3Repository : JpaRepository<Test3, Long> {
}
