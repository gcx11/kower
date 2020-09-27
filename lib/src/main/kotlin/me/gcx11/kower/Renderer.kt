package me.gcx11.kower

interface Renderer {
    val width: Int
    val height: Int

    fun clear(color: Color)

    fun drawTriangle(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float,
        color: Color
    )

    fun drawRectangle(
        x1: Float,
        y1: Float,
        width: Float,
        height: Float,
        color: Color
    )

    fun drawCircle(
        x: Float,
        y: Float,
        radius: Float,
        color: Color
    )

    fun drawLine(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        thickness: Float,
        color: Color
    )

    fun drawArc(
        x: Float,
        y: Float,
        radius: Float,
        startingAngle: Float,
        endingAngle: Float,
        color: Color
    )

    fun drawEmptyTriangle(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float,
        thickness: Float,
        color: Color
    )

    fun drawEmptyArc(
        x: Float,
        y: Float,
        radius: Float,
        startingAngle: Float,
        endingAngle: Float,
        thickness: Float,
        color: Color
    )
}