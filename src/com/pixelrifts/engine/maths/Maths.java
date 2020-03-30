package com.pixelrifts.engine.maths;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public class Maths {
	public static float clamp(float val, float min, float max) {
		if (val < min) return min;
		if (val > max) return max;
		return val;
	}
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float sx,
			float sy, float sz) {
		Matrix4f matrix = new Matrix4f().identity();
		matrix.translate(translation);
		matrix.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0));
		matrix.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0));
		matrix.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1));
		matrix.scale(sx, sy, sz);
		return matrix;
	}

	public static FloatBuffer loadToBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	public static IntBuffer loadToBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	public static Matrix4f createTransformationMatrix(Transformation transform) {
		Vector3f t = transform.getTranslation();
		Vector3f r = transform.getRotation();
		Vector3f s = transform.getScale();
		return createTransformationMatrix(t, r.x, r.y, r.z, s.x, s.y, s.z);
	}
}
