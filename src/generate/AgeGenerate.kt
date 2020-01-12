package generate

import java.util.*

class AgeGenerate() {
    private val currentCalendar: Calendar = Calendar.getInstance()

    init {
        currentCalendar.time = Date()
    }

    fun getAge(birthday: Date): Int {
        val birthdayCalendar = Calendar.getInstance()
        birthdayCalendar.time = birthday

        val currentYear = currentCalendar.get(Calendar.YEAR)
        val birthdayYear = birthdayCalendar.get(Calendar.YEAR)

        return currentYear - birthdayYear
    }
}