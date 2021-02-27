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

    float edge_ratio = ((x-u_center.x)*(x-u_center.x)+(y-u_center.y)*(y-u_center.y)) / (u_radius*u_radius);
    float limit = 1.0 - 0.2 / (1.0 + u_radius*u_radius / 4000.0);
    float alpha = smoothstep(1.0, limit, edge_ratio);

    outColor = vec4(u_color.r, u_color.g, u_color.b, alpha);
}
    """.trimIndent())
}

fun arcFragmentShader(): FragmentShader {
    return FragmentShader("""
#version 300 es
#define PI 3.1415926538

precision highp float;

out vec4 outColor;

uniform vec4 u_color;
uniform vec2 u_screen_size;
uniform vec2 u_resolution;
uniform vec2 u_center;
uniform float u_radius;
uniform float u_starting_angle;
uniform float u_ending_angle;

float atan2(in float y, in float x)
{
    return x == 0.0 ? sign(y)*PI/2.0 : atan(y, x);
}

void main() {
    float x = gl_FragCoord.x;
    float y = u_resolution.y - gl_FragCoord.y;

    float direction_x = x-u_center.x;
    float direction_y = y-u_center.y;

    float edge_ratio = (direction_x*direction_x+direction_y*direction_y) / (u_radius*u_radius);
    float limit = 1.0 - 0.2 / (1.0 + u_radius*u_radius / 4000.0);
    float alpha = smoothstep(1.0, limit, edge_ratio);

    float angle = atan2(direction_y, direction_x);
    if (angle < u_starting_angle || angle > u_ending_angle) discard;

    outColor = vec4(u_color.r, u_color.g, u_color.b, alpha);
}
    """.trimIndent())
}

fun emptyArcFragmentShader(): FragmentShader {
    return FragmentShader("""
#version 300 es
#define PI 3.1415926538

precision highp float;

out vec4 outColor;

uniform vec4 u_color;
uniform vec2 u_screen_size;
uniform vec2 u_resolution;
uniform vec2 u_center;
uniform float u_radius;
uniform float u_thickness;
uniform float u_starting_angle;
uniform float u_ending_angle;

float atan2(in float y, in float x)
{
    return x == 0.0 ? sign(y)*PI/2.0 : atan(y, x);
}

void main() {
    float x = gl_FragCoord.x;
    float y = u_resolution.y - gl_FragCoord.y;

    float direction_x = x-u_center.x;
    float direction_y = y-u_center.y;

    float outer_radius = u_radius + u_thickness / 2.0;
    float inner_radius = u_radius - u_thickness / 2.0;

    float angle = atan2(direction_y, direction_x);
    if (angle < u_starting_angle || angle > u_ending_angle) discard;

    float outer_edge_ratio = (direction_x*direction_x+direction_y*direction_y) / (outer_radius*outer_radius);
    float outer_limit = 1.0 - 0.2 / (1.0 + outer_radius*outer_radius / 2000.0);
    float outer_alpha = smoothstep(1.0, outer_limit, outer_edge_ratio);
    
    float inner_edge_ratio = (direction_x*direction_x+direction_y*direction_y) / (inner_radius*inner_radius);
    float inner_limit = 1.0 - 0.2 / (1.0 + inner_radius*inner_radius / 2000.0);
    float inner_alpha = smoothstep(inner_limit, 1.0, inner_edge_ratio);

    outColor = vec4(u_color.r, u_color.g, u_color.b, min(inner_alpha, outer_alpha));
}
    """.trimIndent())
}