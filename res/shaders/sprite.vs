#version 330 core

layout (location = 0) in vec2 i_pos;
layout (location = 1) in vec2 i_uv;

out vec2 p_uv;

uniform mat4 u_view;
uniform mat4 u_projection;
uniform mat4 u_transformation = mat4(1.0);
uniform vec2 u_uvOffset;
uniform vec2 u_uvScale;

void main() {
	gl_Position = u_projection * u_view * u_transformation * vec4(i_pos, 0.0, 1.0);
	p_uv = (i_uv * u_uvScale) + u_uvOffset;
}
