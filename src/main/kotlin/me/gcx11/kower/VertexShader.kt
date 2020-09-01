package me.gcx11.kower

class VertexShader(
    private val code: String
) {
    fun compile(gl: WebGL2RenderingContext): CompiledVertexShader? {
        val shader = gl.createShader(WebGL2RenderingContext.VERTEX_SHADER)
        gl.shaderSource(shader, code)
        gl.compileShader(shader)
        val success = gl.getShaderParameter(shader, WebGL2RenderingContext.COMPILE_STATUS)
        if (success == true) return CompiledVertexShader(shader)

        println(gl.getShaderInfoLog(shader)) // TODO
        gl.deleteShader(shader)

        return null
    }
}