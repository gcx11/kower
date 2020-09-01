package me.gcx11.kower

fun defaultVertexShader(): VertexShader {
    return VertexShader("""
#version 300 es

precision highp float;

in vec2 a_position;
uniform vec2 u_resolution;

void main() {
    float x = (a_position.x / u_resolution.x) * 2.0 - 1.0;
    float y = (1.0 - a_position.y / u_resolution.y) * 2.0 - 1.0;

    gl_Position = vec4(x, y, 0, 1);
}
    """.trimIndent())
}

fun defaultFragmentShader(): FragmentShader {
    return FragmentShader("""
#version 300 es

precision highp float;

out vec4 outColor;
uniform vec4 u_color;

void main() {
    outColor = u_color;
}
    """.trimIndent())
}

fun circleFragmentShader(): FragmentShader {
    return FragmentShader("""
#version 300 es

precision highp float;

out vec4 outColor;

uniform vec4 u_color;
uniform vec2 u_screen_size;
uniform vec2 u_resolution;
uniform vec2 u_center;
uniform float u_radius;

void main() {
    float x = gl_FragCoord.x;
    float y = u_resolution.y - gl_FragCoord.y;

    if ((x-u_center.x)*(x-u_center.x)+(y-u_center.y)*(y-u_center.y) > u_radius*u_radius) discard;
    outColor = u_color;
}
    """.trimIndent())
}