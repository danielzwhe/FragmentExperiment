package im.danielzwhe.app.fragmentexp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

object Utils {

    fun randomColor(): ColorDrawable {
        return ColorDrawable(getColor())
    }

    private fun getColor(): Int {
        return Color.parseColor(getColorValue())
    }

    private fun getColorValue(): String {
        return StringBuffer("#").apply {
            for (i in 0 until 6) {
                append(randomColorValue())
            }
        }.toString()
    }

    private fun randomColorValue(): String {
        return when (val n = (0..15).random()) {
            10 -> "a"
            11 -> "b"
            12 -> "c"
            13 -> "d"
            14 -> "e"
            15 -> "f"
            else -> "$n"
        }
    }
}