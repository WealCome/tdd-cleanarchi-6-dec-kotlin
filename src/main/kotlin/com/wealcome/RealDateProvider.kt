package com.wealcome

import java.time.LocalDateTime
import java.time.OffsetDateTime

class RealDateProvider : DateProvider {

    override fun now(): Long {
        return LocalDateTime.now()
            .toInstant(OffsetDateTime.now().offset)
            .toEpochMilli()
    }
}
