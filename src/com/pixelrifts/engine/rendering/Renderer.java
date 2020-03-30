package com.pixelrifts.engine.rendering;

import java.util.ArrayList;
import java.util.List;

import com.pixelrifts.engine.base.GameObject;
import com.pixelrifts.engine.basics.Camera;

public class Renderer {
	private static List<GameObject> renderableObjects = new ArrayList<>();
	public static Camera cam = Camera.instance;

	public static void render() {
		SpriteRenderer.render(cam, renderableObjects);
	}

	static void submit(Object o) {
		renderableObjects.add((GameObject) o);
	}
	
	public static void clear() {
		renderableObjects.clear();
	}
}
