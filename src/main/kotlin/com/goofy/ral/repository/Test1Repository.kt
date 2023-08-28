package com.goofy.ral.repository

import com.goofy.ral.entity.Test1
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Test1Repository : JpaRepository<Test1, Long> {
}
