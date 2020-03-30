package com.pixelrifts.engine.objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.HashMap;
import java.util.Map;

import com.pixelrifts.engine.utils.Cleaner;

public class VertexArray {
	private final int id;
	private Map<Integer, VertexBuffer> attributeLists;

	public VertexArray() {
		id = glGenVertexArrays();
		attributeLists = new HashMap<Integer, VertexBuffer>();
		Cleaner.addVertexArray(this);
	}

	public void bind() {
		glBindVertexArray(id);

		for (int attrib : attributeLists.keySet())
			glEnableVertexAttribArray(attrib);
	}

	public void unbind() {
		for (int attrib : attributeLists.keySet())
			glDisableVertexAttribArray(attrib);

		glBindVertexArray(0);
	}

	public void cleanUp() {
		for (VertexBuffer vb : attributeLists.values())
			vb.cleanUp();

		glDeleteVertexArrays(id);
	}

	public void addBuffer(int attrib, VertexBuffer vb) {
		glBindVertexArray(id);
		vb.bind();
		attributeLists.put(attrib, vb);
		glVertexAttribPointer(attrib, vb.getDimensions(), GL_FLOAT, false, 0, 0);
		vb.unbind();
		glBindVertexArray(0);
	}

	@Override
	public String toString() {
		return "VertexArray [id=" + id + ", attributeLists=" + attributeLists + "]";
	}

	public Map<Integer, VertexBuffer> getAttributeLists() {
		return attributeLists;
	}
}
