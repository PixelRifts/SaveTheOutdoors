package com.pixelrifts.engine.basics;

import static com.pixelrifts.engine.basics.Display.*;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import com.pixelrifts.engine.input.Keyboard;
import com.pixelrifts.engine.utils.Buttons;

public class Camera {
	private static final Vector2f initCamPos = new Vector2f(0, 0);
	public static final Matrix4f GUI_PROJECTION_MATRIX = new Matrix4f();
	public static final Camera instance = new Camera(initCamPos);
	
	private static final float SPEED = 2000;

	private Vector2f position;
	private float rot;
	private Matrix4f viewMatrix;
	private Matrix4f projMatrix;

	private ICamMover current;
	private final ICamMover mover2D;

	static {
		calculateGUIProjection();
	}

	private Camera(Vector2f pos) {
		mover2D = this::move2D;
		position = pos;
		viewMatrix = new Matrix4f();
		projMatrix = new Matrix4f();
		calculateViewMatrix();
		calculateProjectionMatrix2D();
		current = mover2D;
	}

	public void setCamMover(ICamMover mover) {
		current = mover;
	}

	public void applyTransfer() {
		CamTransfer transfer = current.move();
		this.position.add(transfer.translation);
		this.rot += transfer.rotation;
		calculateViewMatrix();
	}

	private CamTransfer move2D() {
		Vector2f position = new Vector2f();
		if (Keyboard.instance.isKeyDown(Buttons.GLFW_KEY_W))
			position.y += SPEED * getDelta();
		if (Keyboard.instance.isKeyDown(Buttons.GLFW_KEY_S))
			position.y -= SPEED * getDelta();
		if (Keyboard.instance.isKeyDown(Buttons.GLFW_KEY_A))
			position.x -= SPEED * getDelta();
		if (Keyboard.instance.isKeyDown(Buttons.GLFW_KEY_D))
			position.x += SPEED * getDelta();
		return new CamTransfer(position, 0);
	}

	public Vector2f getPosition() {
		return position;
	}

	public float getRotation() {
		return rot;
	}

	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	public Matrix4f getProjMatrix() {
		return projMatrix;
	}

	private static void calculateGUIProjection() {
		GUI_PROJECTION_MATRIX.identity();
		GUI_PROJECTION_MATRIX.ortho(0, getDisplayWidth(), getDisplayHeight(), 0, 0, 1000);
	}

	public void calculateProjectionMatrix2D() {
		projMatrix.identity();
		projMatrix.ortho2D(-(getDisplayWidth() / 2), getDisplayWidth() / 2, -(getDisplayHeight() / 2),
				getDisplayHeight() / 2);
	}

	public void calculateViewMatrix() {
		viewMatrix.identity();
		Vector3f negativePos = new Vector3f(position.x, position.y, 0).mul(-1);
		viewMatrix.translate(negativePos);
		viewMatrix.rotate(-rot, new Vector3f(0, 0, 1));
	}
}
