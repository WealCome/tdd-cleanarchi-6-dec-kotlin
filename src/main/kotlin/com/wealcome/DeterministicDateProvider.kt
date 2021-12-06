package com.wealcome

class DeterministicDateProvider (private val dateOfNow: Long): DateProvider {

    override fun now(): Long {
        return dateOfNow
    }

}
