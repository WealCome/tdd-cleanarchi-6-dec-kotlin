package com.wealcome

class DateDemo {

    fun newDate(dateProvider: DateProvider): Long {
        return dateProvider.now()
    }

}
