package me.gcx11.kower

class Color(
    val red: Float,
    val green: Float,
    val blue: Float,
    val alpha: Float
) {
    companion object {
        fun fromHex(value: String): Color {
            if (!value.startsWith("#")) {
                throw Exception("Invalid color code $value!")
            }

            val trimmedValue = value.substringAfter("#")
            when (trimmedValue.length) {
                6 -> {
                    var integerValue = trimmedValue.toInt(radix = 16)
                    val b = integerValue % 256
                    integerValue -= b
                    integerValue /= 256
                    val g = integerValue % 256
                    integerValue -= g
                    integerValue /= 256
                    return Color(integerValue / 256f, g / 256f, b / 256f, 1.0f)
                }
                else -> throw Exception("Invalid color code $value!")
            }
        }
    }
}