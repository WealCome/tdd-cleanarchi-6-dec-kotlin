package com.wealcome.alarmmanagement.hexagone.domain.gateways.repositories

import com.wealcome.alarmmanagement.hexagone.domain.models.Alarm

interface AlarmRepository {
    fun next(): List<Alarm>
}
