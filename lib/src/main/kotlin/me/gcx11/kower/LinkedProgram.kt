package me.gcx11.kower

class LinkedProgram(
    val program: WebGLProgram
) {
    fun getAttributeLocation(gl: WebGL2RenderingContext, attributeName: String): Int {
        return gl.getAttribLocation(program, attributeName)
    }

    fun getUniformLocation(gl: WebGL2RenderingContext, uniformName: String): Int {
        return gl.getUniformLocation(program, uniformName)
    }
}