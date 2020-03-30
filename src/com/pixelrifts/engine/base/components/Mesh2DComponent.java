package com.pixelrifts.engine.base.components;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import com.pixelrifts.engine.base.Component;
import com.pixelrifts.engine.objects.Texture;
import com.pixelrifts.engine.objects.VertexArray;
import com.pixelrifts.engine.objects.VertexBuffer;

public class Mesh2DComponent extends Component {
	private VertexArray vao;
	private Texture t;
	private int vertexCount;
	private Vector4f uvOffset;
	
	private float x, y, width, height;

	public Mesh2DComponent() {
		super("Mesh");
		vao = new VertexArray();
		vertexCount = 0;
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		uvOffset = new Vector4f(0, 0, 1, 1);
	}

	private void setMesh(float[] vertices, float[] uvs, int vertexCount, Texture t) {
		vao.bind();
		VertexBuffer positionsBuffer = new VertexBuffer();
		positionsBuffer.addData(vertices, 2);
		vao.addBuffer(0, positionsBuffer);
		VertexBuffer uvsBuffer = new VertexBuffer();
		uvsBuffer.addData(uvs, 2);
		vao.addBuffer(1, uvsBuffer);
		vao.unbind();
		this.vertexCount = vertexCount;
		this.t = t;
	}

	public void setMesh(float x, float y, float width, float height, Texture t) {
		float[] vertices = {
				x - width / 2, y + height / 2, // tl
				x - width / 2, y - height / 2, // bl
				x + width / 2, y - height / 2, // br

				x - width / 2, y + height / 2, // tl
				x + width / 2, y - height / 2, // br
				x + width / 2, y + height / 2, // tr
		};
		float[] uvs = {
				0, 0, // tl
				0, 1, // bl
				1, 1, // br

				0, 0, // tl
				1, 1, // br
				1, 0, // tr
		};
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setMesh(vertices, uvs, 6, t);
	}

	public void render() {
		vao.bind();
		GL15.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
		vao.unbind();
	}
	
	public Texture getTexture() {
		return t;
	}
	
	public void setTexture(Texture t) {
		this.t = t;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public Vector4f getUvOffset() {
		return uvOffset;
	}
	
	public void setUvOffset(Vector4f uvOffset) {
		this.uvOffset = uvOffset;
	}
}
