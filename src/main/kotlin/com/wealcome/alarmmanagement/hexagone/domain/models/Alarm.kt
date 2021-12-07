package com.wealcome.alarmmanagement.hexagone.domain.models

import java.time.LocalDateTime

class Alarm constructor(
    private val localDateTime: LocalDateTime,
    private val active: Boolean
) {

    fun isActive(): Boolean {
        return active
    }

    fun isInactive(): Boolean {
        return !active
    }

    fun time(): LocalDateTime {
        return localDateTime;
    }

    override fun toString(): String {
        return "Alarm(localDateTime=$localDateTime, active=$active)"
    }


}
