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
    private val defaultShaderBundle = DefaultShaderBundle(gl, compiledVertexShader, compiledFragmentShader)
    private val circleShaderBundle = CircleShaderBundle(gl, compiledVertexShader, circleFragmentShader)

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
        val positionBuffer = gl.createBuffer()
        gl.bindBuffer(WebGL2RenderingContext.ARRAY_BUFFER, positionBuffer)

        gl.bufferData(
            WebGL2RenderingContext.ARRAY_BUFFER,
            Float32Array(
                arrayOf(x1, y1, x2, y2, x3, y3)
            ),
            WebGL2RenderingContext.STATIC_DRAW
        )

        val vao = gl.createVertexArray()
        gl.bindVertexArray(vao)

        gl.enableVertexAttribArray(defaultShaderBundle.positionAttributeLocation)
        gl.vertexAttribPointer(defaultShaderBundle.positionAttributeLocation, 2, WebGLRenderingContext.FLOAT, normalized = false, stride = 0, offset = 0)

        gl.useProgram(defaultShaderBundle.linkedProgram.program)

        gl.uniform2f(defaultShaderBundle.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
        gl.uniform4f(defaultShaderBundle.colorUniformLocation, color.red, color.green, color.blue, color.alpha)

        val primitiveType = WebGL2RenderingContext.TRIANGLES
        val offset = 0
        val count = 3
        gl.drawArrays(primitiveType, offset, count)

        gl.deleteVertexArray(vao)
        gl.deleteBuffer(positionBuffer)
    }

    override fun drawRectangle(
        x1: Float,
        y1: Float,
        width: Float,
        height: Float,
        color: Color
    ) {
        val positionBuffer = gl.createBuffer()
        gl.bindBuffer(WebGL2RenderingContext.ARRAY_BUFFER, positionBuffer)

        gl.bufferData(
            WebGL2RenderingContext.ARRAY_BUFFER,
            Float32Array(
                arrayOf(
                    x1, y1, x1 + width, y1, x1, y1 + height,
                    x1 + width, y1, x1, y1 + height, x1 + width, y1 + height
                )
            ),
            WebGL2RenderingContext.STATIC_DRAW
        )

        val vao = gl.createVertexArray()
        gl.bindVertexArray(vao)

        gl.enableVertexAttribArray(defaultShaderBundle.positionAttributeLocation)
        gl.vertexAttribPointer(
            defaultShaderBundle.positionAttributeLocation,
            2,
            WebGLRenderingContext.FLOAT,
            normalized = false,
            stride = 0,
            offset = 0
        )

        gl.useProgram(defaultShaderBundle.linkedProgram.program)

        gl.uniform2f(defaultShaderBundle.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
        gl.uniform4f(defaultShaderBundle.colorUniformLocation, color.red, color.green, color.blue, color.alpha)

        val primitiveType = WebGL2RenderingContext.TRIANGLES
        val offset = 0
        val count = 6
        gl.drawArrays(primitiveType, offset, count)

        gl.deleteVertexArray(vao)
        gl.deleteBuffer(positionBuffer)
    }

    override fun drawCircle(
        x: Float,
        y: Float,
        radius: Float,
        color: Color
    ) {
        val positionBuffer = gl.createBuffer()
        gl.bindBuffer(WebGL2RenderingContext.ARRAY_BUFFER, positionBuffer)

        gl.bufferData(
            WebGL2RenderingContext.ARRAY_BUFFER,
            Float32Array(
                arrayOf(
                    x, y - 2 * radius,
                    x - sqrt(3f) * radius, y + radius,
                    x + sqrt(3f) * radius, y + radius
                )
            ),
            WebGL2RenderingContext.STATIC_DRAW
        )

        val vao = gl.createVertexArray()
        gl.bindVertexArray(vao)

        gl.enableVertexAttribArray(circleShaderBundle.positionAttributeLocation)
        gl.vertexAttribPointer(circleShaderBundle.positionAttributeLocation, 2, WebGLRenderingContext.FLOAT, normalized = false, stride = 0, offset = 0)

        gl.useProgram(circleShaderBundle.linkedProgram.program)

        gl.uniform2f(circleShaderBundle.resolutionUniformLocation, gl.canvas.width.toFloat(), gl.canvas.height.toFloat())
        gl.uniform4f(circleShaderBundle.colorUniformLocation, color.red, color.green, color.blue, color.alpha)
        gl.uniform2f(circleShaderBundle.centerUniformLocation, x, y)
        gl.uniform1f(circleShaderBundle.radiusUniformLocation, radius)

        val primitiveType = WebGL2RenderingContext.TRIANGLES
        val offset = 0
        val count = 3
        gl.drawArrays(primitiveType, offset, count)

        gl.deleteVertexArray(vao)
        gl.deleteBuffer(positionBuffer)
    }
}