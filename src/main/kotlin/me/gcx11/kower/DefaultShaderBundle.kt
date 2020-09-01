package me.gcx11.kower

class DefaultShaderBundle(
    gl: WebGL2RenderingContext,
    compiledVertexShader: CompiledVertexShader,
    compiledFragmentShader: CompiledFragmentShader
): ShaderBundle(gl, compiledVertexShader, compiledFragmentShader) {
    val positionAttributeLocation = linkedProgram.getAttributeLocation(gl, "a_position")
    val resolutionUniformLocation = linkedProgram.getUniformLocation(gl, "u_resolution")
    val colorUniformLocation = linkedProgram.getUniformLocation(gl, "u_color")
}