package me.gcx11.kower

import org.khronos.webgl.ArrayBufferView
import org.w3c.dom.HTMLCanvasElement

external class WebGL2RenderingContext {
    val canvas: HTMLCanvasElement

    fun clear(mask: Int)
    fun clearColor(red: Float, green: Float, blue: Float, alpha: Float)
    fun compileShader(shader: WebGLShader)
    fun createShader(type: Int): WebGLShader
    fun shaderSource(shader: WebGLShader, code: String)
    fun getShaderParameter(shader: WebGLShader, pName: Int): Any?
    fun getShaderInfoLog(shader: WebGLShader): String
    fun createProgram(): WebGLProgram
    fun attachShader(program: WebGLProgram, shader: WebGLShader)
    fun linkProgram(program: WebGLProgram)
    fun getProgramParameter(program: WebGLProgram, pName: Int): Any?
    fun deleteShader(shader: WebGLShader)
    fun deleteProgram(program: WebGLProgram)
    fun getProgramInfoLog(program: WebGLProgram): String
    fun getAttribLocation(program: WebGLProgram, name: String): Int
    fun getUniformLocation(program: WebGLProgram, name: String): Int
    fun createBuffer(): WebGLBuffer
    fun bindBuffer(target: Int, buffer: WebGLBuffer)
    fun deleteBuffer(buffer: WebGLBuffer)
    fun bufferData(target: Int, srcData: ArrayBufferView, usage: Int)
    fun createVertexArray(): WebGLVertexArrayObject
    fun bindVertexArray(vertexArray: WebGLVertexArrayObject)
    fun deleteVertexArray(vertexArray: WebGLVertexArrayObject)
    fun enableVertexAttribArray(index: Int)
    fun vertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, offset: Int)
    fun viewport(x: Int, y: Int, width: Int, height: Int)
    fun useProgram(program: WebGLProgram)
    fun drawArrays(mode: Int, first: Int, count: Int)
    fun uniform1f(location: Int, v1: Float)
    fun uniform2f(location: Int, v1: Float, v2: Float)
    fun uniform4f(location: Int, v1: Float, v2: Float, v3: Float, v4: Float)

    companion object {
        val ARRAY_BUFFER: Int
        val COLOR_BUFFER_BIT: Int
        val COMPILE_STATUS: Int
        val FRAGMENT_SHADER: Int
        val VERTEX_SHADER: Int
        val LINK_STATUS: Int
        val STATIC_DRAW: Int
        val TRIANGLES: Int
    }
}

external class WebGLBuffer
external class WebGLProgram
external class WebGLShader
external class WebGLVertexArrayObject