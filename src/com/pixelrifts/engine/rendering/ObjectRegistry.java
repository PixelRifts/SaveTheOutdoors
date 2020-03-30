package com.pixelrifts.engine.rendering;

import java.util.ArrayList;
import java.util.List;

import com.pixelrifts.engine.base.GameObject;
import com.pixelrifts.engine.worlds.InteractionWorld;

public class ObjectRegistry {
	private static List<GameObject> objects = new ArrayList<>();

	public static void update() {
		Renderer.clear();
		InteractionWorld.clear();

		for (GameObject object : objects) {
			object.update();
			if (object.hasComponent("Mesh")) {
				Renderer.submit(object);
			}
			if (object.hasComponent("Collider")) {
				InteractionWorld.submitObject(object);
			}
		}
	}

	public static void submitGameObject(GameObject object) {
		objects.add(object);
	}
}
