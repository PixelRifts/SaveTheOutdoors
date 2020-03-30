package com.pixelrifts.engine.objects;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class VertexBuffer {
	private final int id;
	private int dimensions;
	private float[] data;

	public VertexBuffer() {
		id = glGenBuffers();
	}

	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, id);
	}

	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void cleanUp() {
		glDeleteBuffers(id);
	}

	public int getId() {
		return id;
	}

	public void addData(float[] data, int dimensions) {
		this.dimensions = dimensions;
		this.data = data;
		bind();
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		unbind();
	}

	public int getDimensions() {
		return dimensions;
	}

	public float[] getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			t.append(data[i]).append(" ");
		}
		return t.toString();
	}
}
