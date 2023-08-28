package com.goofy.ral.repository

import com.goofy.ral.entity.Test2
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Test2Repository : JpaRepository<Test2, Long> {
}
