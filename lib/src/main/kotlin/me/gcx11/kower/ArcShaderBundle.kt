package me.gcx11.kower

class ArcShaderBundle(
    gl: WebGL2RenderingContext,
    compiledVertexShader: CompiledVertexShader,
    compiledFragmentShader: CompiledFragmentShader
): ShaderBundle(gl, compiledVertexShader, compiledFragmentShader), HasPositionAttribute {
    override val positionAttributeLocation = linkedProgram.getAttributeLocation(gl, "a_position")
    val resolutionUniformLocation = linkedProgram.getUniformLocation(gl, "u_resolution")
    val colorUniformLocation = linkedProgram.getUniformLocation(gl, "u_color")
    val centerUniformLocation = linkedProgram.getUniformLocation(gl, "u_center")
    val radiusUniformLocation = linkedProgram.getUniformLocation(gl, "u_radius")
    val startingAngleUniformLocation = linkedProgram.getUniformLocation(gl, "u_starting_angle")
    val endingAngleUniformLocation = linkedProgram.getUniformLocation(gl, "u_ending_angle")
}