package com.pixelrifts.engine.objects;

import static org.lwjgl.opengl.GL15.*;

import java.nio.IntBuffer;

import com.pixelrifts.engine.maths.Maths;
import com.pixelrifts.engine.utils.Cleaner;

public class IndexBuffer {
	private final int id;
	private int dimensions;
	private int drawCount;
	private int[] indices;

	public IndexBuffer() {
		id = glGenBuffers();
		Cleaner.addIndexBuffer(this);
	}

	public void bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
	}

	public void unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public int getDrawCount() {
		return drawCount;
	}

	public void cleanUp() {
		glDeleteBuffers(id);
	}

	public int getId() {
		return id;
	}

	public int getDimensions() {
		return dimensions;
	}

	public int[] getIndices() {
		return indices;
	}

	public IntBuffer toBuffer() {
		return Maths.loadToBuffer(indices);
	}

	public void addIndices(int[] data) {
		this.indices = data;
		bind();
		IntBuffer buffer = Maths.loadToBuffer(data);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		unbind();
	}
}
