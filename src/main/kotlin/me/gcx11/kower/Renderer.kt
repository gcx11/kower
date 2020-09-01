package me.gcx11.kower

interface Renderer {
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
}