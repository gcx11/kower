package me.gcx11.kower

import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import kotlin.math.sqrt

class WebGL2Renderer(
    private val gl: WebGL2RenderingContext
): Renderer {
    private val compiledVertexShader = defaultVertexShader().compile(gl)!!
    private val compiledFragmentShader = defaultFragmentShader().compile(gl)!!
    private val circleFragmentShader = circleFragmentShader().compile(gl)!!
    private val arcFragmentShader = arcFragmentShader().compile(gl)!!
    private val emptyArcFragmentShader = emptyArcFragmentShader().compile(gl)!!
    private val defaultShaderBundle = DefaultShaderBundle(gl, compiledVertexShader, compiledFragmentShader)
    private val circleShaderBundle = CircleShaderBundle(gl, compiledVertexShader, circleFragmentShader)
    private val arcShaderBundle = ArcShaderBundle(gl, compiledVertexShader, arcFragmentShader)
    private val emptyArcShaderBundle = EmptyArcShaderBundle(gl, compiledVertexShader, emptyArcFragmentShader)

    init {
        gl.enable(WebGL2RenderingContext.BLEND)
        gl.blendFunc(WebGL2RenderingContext.SRC_ALPHA, WebGL2RenderingContext.ONE_MINUS_SRC_ALPHA)
    }

    override val width: Int
        get() = gl.canvas.clientWidth

    override val height: Int
        get() = gl.canvas.clientHeight

    override fun clear(color: Color) {
        gl.viewport(0, 0, gl.canvas.width, gl.canvas.height)
        gl.clearColor(color.red, color.green, color.blue, color.alpha)
        gl.clear(WebGL2RenderingContext.COLOR_BUFFER_BIT)
    }

    override fun drawTriangle(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float,
        color: Color
    ) {
        val points = arrayOf(x1, y1, x2, y2, x3, y3)

        drawTriangles(
            defaultShaderBundle,
            points
        ) {
            gl.uniform2f(it.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
            gl.uniform4f(it.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
        }
    }

    override fun drawRectangle(
        x1: Float,
        y1: Float,
        width: Float,
        height: Float,
        color: Color
    ) {
        val points = arrayOf(
            x1, y1, x1 + width, y1, x1, y1 + height,
            x1 + width, y1, x1, y1 + height, x1 + width, y1 + height
        )

        drawTriangles(
            defaultShaderBundle,
            points
        ) {
            gl.uniform2f(it.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
            gl.uniform4f(it.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
        }
    }

    override fun drawCircle(
        x: Float,
        y: Float,
        radius: Float,
        color: Color
    ) {
        val points = arrayOf(
            x, y - 2 * radius,
            x - sqrt(3f) * radius, y + radius,
            x + sqrt(3f) * radius, y + radius
        )

        drawTriangles(
            circleShaderBundle,
            points
        ) {
            gl.uniform2f(it.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
            gl.uniform4f(it.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
            gl.uniform2f(it.centerUniformLocation, x, y)
            gl.uniform1f(it.radiusUniformLocation, radius)
        }
    }

    override fun drawLine(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        thickness: Float,
        color: Color
    ) {
        val points = computeLinePoints(x1, y1, x2, y2, thickness)

        drawTriangles(
            defaultShaderBundle,
            points
        ) {
            gl.uniform2f(it.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
            gl.uniform4f(it.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
        }
    }

    override fun drawArc(
        x: Float,
        y: Float,
        radius: Float,
        startingAngle: Float,
        endingAngle: Float,
        color: Color
    ) {
        val points = arrayOf(
            x, y - 2 * radius,
            x - sqrt(3f) * radius, y + radius,
            x + sqrt(3f) * radius, y + radius
        )

        drawTriangles(
            arcShaderBundle,
            points
        ) {
            gl.uniform2f(it.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
            gl.uniform4f(it.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
            gl.uniform2f(it.centerUniformLocation, x, y)
            gl.uniform1f(it.radiusUniformLocation, radius)
            gl.uniform1f(it.startingAngleUniformLocation, startingAngle)
            gl.uniform1f(it.endingAngleUniformLocation, endingAngle)
        }
    }

    override fun drawEmptyTriangle(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float,
        thickness: Float,
        color: Color
    ) {
        val points =
            computeLinePoints(x1, y1, x2, y2, thickness) +
            computeLinePoints(x2, y2, x3, y3, thickness) +
            computeLinePoints(x3, y3, x1, y1, thickness)

        drawTriangles(
            defaultShaderBundle,
            points
        ) {
            gl.uniform2f(it.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
            gl.uniform4f(it.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
        }
    }

    override fun drawEmptyArc(
        x: Float,
        y: Float,
        radius: Float,
        startingAngle: Float,
        endingAngle: Float,
        thickness: Float,
        color: Color
    ) {
        val points = arrayOf(
            x, y - 2 * (radius + thickness / 2),
            x - sqrt(3f) * (radius + thickness / 2), y + (radius + thickness / 2),
            x + sqrt(3f) * (radius + thickness / 2), y + (radius + thickness / 2)
        )

        drawTriangles(
            emptyArcShaderBundle,
            points
        ) {
            gl.uniform2f(it.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
            gl.uniform4f(it.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
            gl.uniform2f(it.centerUniformLocation, x, y)
            gl.uniform1f(it.radiusUniformLocation, radius)
            gl.uniform1f(it.thicknessUniformLocation, thickness)
            gl.uniform1f(it.startingAngleUniformLocation, startingAngle)
            gl.uniform1f(it.endingAngleUniformLocation, endingAngle)
        }
    }

    private fun <T> drawTriangles(
        shaderBundle: T,
        points: Array<Float>,
        uniformBody: (T) -> Unit
    ) where T: ShaderBundle, T: HasPositionAttribute {
        val positionBuffer = gl.createBuffer()
        gl.bindBuffer(WebGL2RenderingContext.ARRAY_BUFFER, positionBuffer)

        gl.bufferData(
            WebGL2RenderingContext.ARRAY_BUFFER,
            Float32Array(points),
            WebGL2RenderingContext.STATIC_DRAW
        )

        val vao = gl.createVertexArray()
        gl.bindVertexArray(vao)

        gl.enableVertexAttribArray(shaderBundle.positionAttributeLocation)
        gl.vertexAttribPointer(shaderBundle.positionAttributeLocation, 2, WebGLRenderingContext.FLOAT, normalized = false, stride = 0, offset = 0)

        gl.useProgram(shaderBundle.linkedProgram.program)

        uniformBody(shaderBundle)

        val primitiveType = WebGL2RenderingContext.TRIANGLES
        val offset = 0
        val count = points.size / 2
        gl.drawArrays(primitiveType, offset, count)

        gl.deleteVertexArray(vao)
        gl.deleteBuffer(positionBuffer)
    }

    private fun computeLinePoints(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        thickness: Float
    ): Array<Float> {
        val deltaX = x2 - x1
        val deltaY = y2 - y1
        val length = sqrt(deltaX*deltaX + deltaY*deltaY)
        val halfThickness = thickness / 2f

        val normalAX = -deltaY * (halfThickness / length)
        val normalAY = deltaX * (halfThickness / length)

        val normalBX = deltaY * (halfThickness / length)
        val normalBY = -deltaX * (halfThickness / length)

        return arrayOf(
            x1 + normalAX, y1 + normalAY,
            x2 + normalAX, y2 + normalAY,
            x2 + normalBX, y2 + normalBY,
            x2 + normalBX, y2 + normalBY,
            x1 + normalBX, y1 + normalBY,
            x1 + normalAX, y1 + normalAY
        )
    }
}