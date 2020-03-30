package com.pixelrifts.engine.maths;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation
{
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;

	public Transformation(Vector3f translation, Vector3f rotation, Vector3f scale)
	{
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Transformation()
	{
		this.translation = new Vector3f();
		this.rotation = new Vector3f();
		this.scale = new Vector3f(1, 1, 1);
	}

	public Vector3f getTranslation()
	{
		return translation;
	}

	public void setTranslation(Vector3f translation)
	{
		this.translation = translation;
	}

	public void changeTranslation(Vector3f translation)
	{
		this.translation.add(translation);
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public void setRotation(Vector3f rotation)
	{
		this.rotation = rotation;
	}

	public void changeRotation(Vector3f rotation)
	{
		this.rotation.add(rotation);
	}

	public Vector3f getScale()
	{
		return scale;
	}

	public void setScale(Vector3f scale)
	{
		this.scale = scale;
	}

	public void changeScale(Vector3f scale)
	{
		this.scale.add(scale);
	}
	
	public Matrix4f toMatrix()
	{
		return Maths.createTransformationMatrix(this);
	}

	@Override
	public String toString()
	{
		return "Transformation [translation=" + translation + ", rotation=" + rotation + ", scale=" + scale + "]";
	}
}
