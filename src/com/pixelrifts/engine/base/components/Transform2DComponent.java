package com.pixelrifts.engine.base.components;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import com.pixelrifts.engine.base.Component;

public class Transform2DComponent extends Component {
	private Vector2f position;
	private float rotation;
	private Vector2f scale;
	
	private Matrix4f transformMatrix;
	
	public Transform2DComponent() {
		super("Transform");
		transformMatrix = new Matrix4f();
		position = new Vector2f(0, 0);
		rotation = 0;
		scale = new Vector2f(1, 1);
		calculateTransformMatrix();
	}
	
	public void translate(Vector2f d) {
		position.add(d);
		calculateTransformMatrix();
	}

	public void translate(float dx, float dy) {
		position.x += dx;
		position.y += dy;
		calculateTransformMatrix();
	}

	public void rotate(float r) {
		this.rotation += r;
		calculateTransformMatrix();
	}
	
	public void scale(Vector2f s) {
		scale.add(s);
		calculateTransformMatrix();
	}
	
	public void scale(float sx, float sy) {
		scale.x += sx;
		scale.y += sy;
		calculateTransformMatrix();
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
		calculateTransformMatrix();
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		calculateTransformMatrix();
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
		calculateTransformMatrix();
	}

	private void calculateTransformMatrix() {
		transformMatrix.identity();
		transformMatrix.translate(new Vector3f(position.x, position.y, 0));
		transformMatrix.rotate(rotation, new Vector3f(0, 0, 1));
		transformMatrix.scale(new Vector3f(scale.x, scale.y, 0));
	}
	
	public Matrix4f toMatrix() {
		return transformMatrix;
	}
}
