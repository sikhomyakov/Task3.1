fun main() {

    println(agoToText(60 * 4))
}

fun isJustNow(secondCount: Int): Boolean {
    return (secondCount >= 0) && (secondCount < 60)
}

fun isMinuts(secondCount: Int): Boolean {
    return (secondCount >= 60) && (secondCount < 60 * 60)
}

fun isHours(secondCount: Int): Boolean {
    return (secondCount >= 60 * 60) && (secondCount < 24 * 60 * 60)
}

fun isToday(secondCount: Int): Boolean {
    return (secondCount >= 24 * 60 * 60) && (secondCount < 2 * 24 * 60 * 60)
}

fun isYesterday(secondCount: Int): Boolean {
    return (secondCount >= 2 * 24 * 60 * 60) && (secondCount < 3 * 24 * 60 * 60)
}

fun isLongTimeAgo(secondCount: Int): Boolean {
    return secondCount >= 24 * 60 * 60 * 3
}

fun agoToText(secondCount: Int): String {
    val result: String = when {
        isJustNow(secondCount) -> "был(а) только что"
        isMinuts(secondCount) -> convertMinutes(secondCount)
        isHours(secondCount) -> convertHours(secondCount)
        isToday(secondCount) -> "был(а) сегодня"
        isYesterday(secondCount) -> "был(а) вчера"
        isLongTimeAgo(secondCount) -> "был(а) давно"
        else -> "hz"
    }
    return result
}

fun convertMinutes(seconds: Int): String {
    val minutes = seconds / 60
    val minutesCase: String = when {
        (minutes % 10 == 1 && minutes != 11) -> "минуту"
        ((minutes % 10 == 2 || minutes % 10 == 3 || minutes % 10 == 4) &&
                (minutes != 12) && (minutes != 13) && (minutes != 14)) -> "минуты"
        else -> "минут"
    }

    if (minutes == 1)
        return "минуту назад"

    return "был(а) $minutes $minutesCase назад"
}

fun convertHours(seconds: Int): String {
    val hours = seconds / 3600
    val hoursCase = when {
        (hours == 1 || hours == 21) -> "час"
        (hours > 1 && hours < 5 || hours > 21 && hours < 25) -> "часа"
        else -> "часов"
    }
    return "был(а) $hours $hoursCase назад"
}