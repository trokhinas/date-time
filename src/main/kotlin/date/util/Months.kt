package date.util

enum class Months(val days: Int, val code: Int) {
    Jan(31, 1),
    Feb(28, 4),
    Mar(31, 4),
    Apr(30, 0),
    May(31, 2),
    Jun(30, 5),
    Jul(31, 0),
    Aug(31, 3),
    Sep(30, 6),
    Oct(31, 1),
    Nov(30, 4),
    Dec(31, 6)
}