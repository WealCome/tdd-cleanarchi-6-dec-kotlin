package com.wealcome

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloTest {

    @Test
    fun `should say Hello`() {
        assertThat(Hello().sayHello()).isEqualTo("Hello")
    }

}
