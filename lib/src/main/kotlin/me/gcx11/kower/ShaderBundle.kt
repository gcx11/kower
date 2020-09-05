package me.gcx11.kower

open class ShaderBundle(
    gl: WebGL2RenderingContext,
    compiledVertexShader: CompiledVertexShader,
    compiledFragmentShader: CompiledFragmentShader
) {
    val linkedProgram = Program(compiledVertexShader, compiledFragmentShader).linkProgram(gl)!!
}