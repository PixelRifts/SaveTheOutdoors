package com.pixelrifts.engine.base;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector4f;

import com.pixelrifts.engine.objects.Texture;

public class Animation {
	private Texture t;
	private List<Vector4f> offsetsAndScales;
	private Vector4f current;

	private int currentIndex;
	private float speed;

	private boolean active;
	private boolean loop;

	public Animation(boolean loop, float speed) {
		offsetsAndScales = new ArrayList<>();
		current = new Vector4f(0, 0, 1, 1);
		currentIndex = 0;
		this.loop = loop;
		this.speed = speed;
		this.active = true;
		reset();
	}

	public void setTexture(Texture texture, int size) {
		t = texture;
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				float startX = i * (texture.getWidth() / size);
				float startY = j * (texture.getHeight() / size);
				Vector4f vc = new Vector4f(startX / texture.getWidth(), startY / texture.getHeight(), 1f / size,
						1f / size);
				offsetsAndScales.add(vc);
			}
		}
		reset();
	}

	public Vector4f update(float time) {
		if (active) {
			if (time % speed == 0) {
				if (currentIndex == offsetsAndScales.size()) {
					if (loop)
						reset();
					else {
						active = false;
						return current;
					}
				}
				current = offsetsAndScales.get(currentIndex);
				currentIndex++;
			}
			return current;
		}
		return null;
	}

	public boolean isActive() {
		return active;
	}

	public Texture getTexture() {
		return t;
	}

	public void reset() {
		currentIndex = 0;
		active = true;
	}

	public void setActive() {
		active = true;
	}
}
