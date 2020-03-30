#version 330 core

in vec2 p_uv;

uniform sampler2D u_textureSampler;

void main() {
	gl_FragColor = texture(u_textureSampler, p_uv);
}
