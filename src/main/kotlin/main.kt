fun main() {
    val seconds = 180
    println(agoToText(seconds))
}

fun isJustNow(secondCount: Int): Boolean {
    return (secondCount < 60) && (secondCount >= 0)
}

fun isMinuts(secondCount: Int): Boolean {
    return (secondCount < 60 * 60) && (secondCount >= 60)
}

fun isHours(secondCount: Int): Boolean {
    return (secondCount < 24 * 60 * 60) && (secondCount >= 60 * 60 + 1)
}

fun isToday(secondCount: Int): Boolean {
    return (secondCount < 24 * 60 * 60 * 2) && (secondCount >= 24 * 60 * 60 + 1)
}

fun isYesterday(secondCount: Int): Boolean {
    return (secondCount < 24 * 60 * 60 * 3) && (secondCount >= 24 * 60 * 60 * 2 + 1)
}

fun isLongTimeAgo(secondCount: Int): Boolean {
    return secondCount >= 24 * 60 * 60 * 3
}

fun agoToText(secondCount: Int): String {
    val result: String = when {
        isJustNow(secondCount) -> "был(а) только что"
        isMinuts(secondCount) -> convertTime(secondCount)
        isHours(secondCount) -> convertTime(secondCount)
        isToday(secondCount) -> "был(а) сегодня"
        isYesterday(secondCount) -> "был(а) вчера"
        isLongTimeAgo(secondCount) -> "был(а) давно"
        else -> "hz"
    }
    return result

}

fun convertTime(publishAgo: Int): String {
    fun convertHours(minutes: Int): String {
        val hours = minutes / 60

        if (hours < 24) {
            val hoursCase: String = when {
                (hours == 1 || hours == 21) -> "час"
                (hours > 1 && hours < 5 || hours > 21 && hours < 25) -> "часа"
                else -> "часов"
            }

            return "был(а) $hours $hoursCase назад"

        }
        return convertHours(minutes)
    }

    fun convertMinutes(seconds: Int): String {
        val minutes = seconds / 60

        if (minutes < 60) {
            if (minutes == 0)
                return "менее минуты назад"
            else if (minutes < 60) {
                val minutesCase: String = when {
                    (minutes % 10 == 1 && minutes != 11) -> "минуту"
                    ((minutes % 10 == 2 || minutes % 10 == 3 || minutes % 10 == 4) &&
                            (minutes != 12) && (minutes != 13) && (minutes != 14)) -> "минуты"
                    else -> "минут"
                }

                if (minutes == 1)
                    return "минуту назад"

                return "$minutes $minutesCase назад"
            }
        }
        return convertMinutes(seconds)

    }

    return convertMinutes(publishAgo)
}