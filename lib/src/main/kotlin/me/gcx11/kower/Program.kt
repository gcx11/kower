package me.gcx11.kower

class Program(
    private val vertexShader: CompiledVertexShader,
    private val fragmentShader: CompiledFragmentShader
) {
    fun linkProgram(gl: WebGL2RenderingContext): LinkedProgram? {
        val program = gl.createProgram()
        gl.attachShader(program, vertexShader.shader)
        gl.attachShader(program, fragmentShader.shader)
        gl.linkProgram(program)
        val success = gl.getProgramParameter(program, WebGL2RenderingContext.LINK_STATUS)
        if (success == true) return LinkedProgram(program)

        println(gl.getProgramInfoLog(program)) // TODO
        gl.deleteProgram(program)

        return null
    }
}