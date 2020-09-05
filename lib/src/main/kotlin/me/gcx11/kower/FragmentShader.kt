package me.gcx11.kower

class FragmentShader(
    private val code: String
) {
    fun compile(gl: WebGL2RenderingContext): CompiledFragmentShader? {
        val shader = gl.createShader(WebGL2RenderingContext.FRAGMENT_SHADER)
        gl.shaderSource(shader, code)
        gl.compileShader(shader)
        val success = gl.getShaderParameter(shader, WebGL2RenderingContext.COMPILE_STATUS)
        if (success == true) return CompiledFragmentShader(shader)

        println(gl.getShaderInfoLog(shader)) // TODO
        gl.deleteShader(shader)

        return null
    }
}