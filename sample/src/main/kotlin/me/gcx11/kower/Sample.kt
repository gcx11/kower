package me.gcx11.kower

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAnimationFrame
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.PI

fun main() {
    window.onload = {
        val canvas = document.createElement("canvas") as HTMLCanvasElement
        val gl = canvas.getContext("webgl2") as WebGL2RenderingContext? ?: throw Exception("WebGl2 context not found!")

        document.body!!.appendChild(canvas)

        val width = gl.canvas.clientWidth
        val height = gl.canvas.clientHeight
        gl.canvas.width = width
        gl.canvas.height = height

        val renderer = WebGL2Renderer(gl)

        GlobalScope.launch {
            while (true) {
                window.awaitAnimationFrame()
                renderer.clear(Color.fromHex("#ebe2d8"))
                renderer.drawRectangle(200f, 200f, 500f, 500f, Color.fromHex("#aa2222"))
                renderer.drawTriangle(100f, 100f, 500f, 100f, 100f, 500f, Color.fromHex("#aaffdd"))
                renderer.drawEmptyTriangle(100f, 100f, 500f, 100f, 100f, 500f, thickness = 2f, Color.fromHex("#ffffdd"))
                renderer.drawCircle(500f, 500f, 100f, Color.fromHex("#00ffff"))
                renderer.drawCircle(500f, 500f, 40f, Color.fromHex("#000000"))
                renderer.drawLine(300f, 200f, 400f, 300f, 5f, Color.fromHex("#abcdef"))
                renderer.drawArc(800f, 400f, 100f, -PI.toFloat() / 2f, PI.toFloat() / 2f, Color.fromHex("#dd5555"))
                renderer.drawEmptyArc(600f, 200f, 50f, -PI.toFloat(), PI.toFloat(), 20f, Color.fromHex("#66ab66"))
            }
        }
    }
}