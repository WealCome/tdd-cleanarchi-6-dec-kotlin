package com.wealcome.unit.alarmselection

import com.wealcome.alarmmanagement.hexagone.domain.models.DeterministicDateProvider
import com.wealcome.alarmmanagement.adapters.secondary.gateways.repositories.InMemoryAlarmRepository
import com.wealcome.alarmmanagement.hexagone.domain.models.Alarm
import com.wealcome.alarmmanagement.hexagone.usecase.alarmselection.selectAlarmToDisplay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SelectAlarmToDisplayTest {

    private val alarmRepository = InMemoryAlarmRepository()
    private val dateProvider =
        DeterministicDateProvider(LocalDateTime.of(2020, 3, 4, 10, 3, 4))

    @Nested
    inner class NoAlarmAvailableInTheFuture {

        @Test
        fun `should not select any alarm`() {
            assertNoAlarmToDisplay()
        }

    }

    @Nested
    inner class SomeAlarmsAvailableInTheFuture {

        @Nested
        inner class OneActiveAlarmInTheFuture {

            @Test
            fun `should select it`() {
                val nextAlarm = Alarm(LocalDateTime.of(2020, 3, 4, 15, 3), true)
                alarmRepository.setNextOnes(nextAlarm)
                assertNextAlarmToDisplay(nextAlarm)
            }
        }

        @Nested
        inner class OneInactiveAlarmInTheFuture {

            @Test
            fun `should select it`() {
                val nextAlarm = Alarm(LocalDateTime.of(2020, 3, 4, 15, 3), false)
                alarmRepository.setNextOnes(nextAlarm)
                assertNextAlarmToDisplay(nextAlarm)
            }
        }

        @Nested
        inner class SeveralActiveAlarmsInTheFuture {

            @Test
            fun `should select the first one`() {
                val nextAlarm1 = Alarm(LocalDateTime.of(2020, 3, 4, 15, 3), true)
                val nextAlarm2 = Alarm(LocalDateTime.of(2020, 3, 4, 16, 3), true)
                alarmRepository.setNextOnes(nextAlarm2, nextAlarm1)
                assertNextAlarmToDisplay(nextAlarm1)
            }

            @Nested
            inner class DisableSomeAlarms {

                @Test
                fun `should disable first alarm and select the next active one`() {
                    val nextAlarm1 = Alarm(LocalDateTime.of(2020, 3, 4, 15, 3), false)
                    val nextAlarm2 = Alarm(LocalDateTime.of(2020, 3, 4, 16, 3), true)
                    alarmRepository.setNextOnes(nextAlarm2, nextAlarm1)
                    assertNextAlarmToDisplay(nextAlarm2)
                }

                @Test
                fun `should disable all alarms first and then select the first one in the future`() {
                    val nextAlarm1 = Alarm(LocalDateTime.of(2020, 3, 4, 15, 3), false)
                    val nextAlarm2 = Alarm(LocalDateTime.of(2020, 3, 4, 16, 3), false)
                    alarmRepository.setNextOnes(nextAlarm2, nextAlarm1)
                    assertNextAlarmToDisplay(nextAlarm1)
                }

                @Test
                fun `should disable all alarms first and then select no one if all active ones are in the past`() {
                    val nextAlarm1 = Alarm(LocalDateTime.of(2020, 3, 4, 8, 3), false)
                    val nextAlarm2 = Alarm(LocalDateTime.of(2020, 3, 4, 16, 3), false)
                    alarmRepository.setNextOnes(nextAlarm2, nextAlarm1)
                    assertNoAlarmToDisplay()
                }

                @Test
                fun `should disable some alarms first and then select no one because no active in the future`() {
                    val nextAlarm1 = Alarm(LocalDateTime.of(2020, 3, 4, 8, 3), true)
                    val nextAlarm2 = Alarm(LocalDateTime.of(2020, 3, 4, 16, 3), false)
                    alarmRepository.setNextOnes(nextAlarm2, nextAlarm1)
                    assertNoAlarmToDisplay()
                }

            }

        }

    }

    fun assertNoAlarmToDisplay() = assertThat(selectAlarmToDisplay(alarmRepository, dateProvider)).isNull()
    fun assertNextAlarmToDisplay(alarm: Alarm) =
        assertThat(selectAlarmToDisplay(alarmRepository, dateProvider)).isEqualTo(alarm)!!

}
