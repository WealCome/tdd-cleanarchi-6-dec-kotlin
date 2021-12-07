package com.wealcome.alarmmanagement.hexagone.usecase.alarmselection

import com.wealcome.alarmmanagement.hexagone.domain.gateways.repositories.AlarmRepository
import com.wealcome.alarmmanagement.hexagone.domain.models.Alarm
import com.wealcome.alarmmanagement.hexagone.domain.models.DateProvider


fun selectAlarmToDisplay(
    alarmRepository: AlarmRepository,
    dateProvider: DateProvider
): Alarm? {
    val alarms: List<Alarm> = alarmRepository.next()
    val sortedAlarms = sortAlarmsFromTheMostDistantToTheClosestOne(alarms)
    if (noAlarmsAvailable(alarms))
        return null
    if (areAllAlarmsInactive(sortedAlarms))
        return if (willAlarmTriggerInTheFuture(closestAlarm(sortedAlarms), dateProvider))
            closestAlarm(sortedAlarms)
        else null
    return closestActiveOneInTheFuture(sortedAlarms, dateProvider)
}

private fun closestActiveOneInTheFuture(
    sortedAlarms: List<Alarm>,
    dateProvider: DateProvider
) = sortedAlarms.find { it.isActive() && willAlarmTriggerInTheFuture(it, dateProvider) }

private fun noAlarmsAvailable(nextAlarms: List<Alarm>) =
    nextAlarms.isEmpty()

private fun areAllAlarmsInactive(sortedNextAlarms: List<Alarm>) =
    sortedNextAlarms.stream().allMatch { it.isInactive() }

private fun sortAlarmsFromTheMostDistantToTheClosestOne(nextAlarms: List<Alarm>) =
    nextAlarms.sortedBy { alarm: Alarm -> alarm.time() }

private fun closestAlarm(sortedNextAlarms: List<Alarm>) =
    sortedNextAlarms[0]

fun willAlarmTriggerInTheFuture(alarm: Alarm, dateProvider: DateProvider) =
    dateProvider.now().isBefore(alarm.time())

