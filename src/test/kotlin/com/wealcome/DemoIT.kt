package com.wealcome

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DemoIT {

    @Test
    fun doNothing2() {
        Assertions.assertThat(2).isEqualTo(2)
    }

}