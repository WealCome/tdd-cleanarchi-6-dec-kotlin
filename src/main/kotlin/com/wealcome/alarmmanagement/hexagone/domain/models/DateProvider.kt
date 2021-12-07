package com.wealcome.alarmmanagement.hexagone.domain.models

import java.time.LocalDateTime

interface DateProvider {

    fun now(): LocalDateTime

}
