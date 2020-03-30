package com.pixelrifts.engine.basics;

import org.joml.Vector2f;

public class CamTransfer {
	public final Vector2f translation;
	public final float rotation;

	public CamTransfer(Vector2f translation, float rotation) {
		this.translation = translation;
		this.rotation = rotation;
	}
}
