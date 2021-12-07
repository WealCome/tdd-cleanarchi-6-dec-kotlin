package com.wealcome.alarmmanagement.adapters.secondary.gateways.repositories

import com.wealcome.alarmmanagement.hexagone.domain.gateways.repositories.AlarmRepository
import com.wealcome.alarmmanagement.hexagone.domain.models.Alarm

class InMemoryAlarmRepository : AlarmRepository {

    private var nextAlarms: MutableList<Alarm> = mutableListOf()

    override fun next(): List<Alarm> {
        return nextAlarms
    }

    fun setNextOnes(vararg nextAlarms: Alarm) {
        for (nextAlarm in nextAlarms) {
            this.nextAlarms.add(nextAlarm)
        }
    }

}
