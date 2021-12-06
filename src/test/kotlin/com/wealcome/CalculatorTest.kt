package com.wealcome

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.OffsetDateTime

class CalculatorTest {

    @Test
    fun `should return date of now`() {
        val dateProvider = DeterministicDateProvider(
            LocalDateTime.of(2020, 3, 4, 14, 15, 2, 12)
                .toInstant(OffsetDateTime.now().offset)
                .toEpochMilli()
        )
        assertThat(DateDemo().newDate(dateProvider)).isEqualTo(dateProvider.now())
    }

}
