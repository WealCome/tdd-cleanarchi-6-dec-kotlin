package com.wealcome.alarmmanagement.hexagone.domain.models

import java.time.LocalDateTime

class DeterministicDateProvider (private val dateOfNow: LocalDateTime): DateProvider {

    override fun now(): LocalDateTime {
        return dateOfNow
    }

}
