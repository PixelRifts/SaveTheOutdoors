package com.pixelrifts.engine.worlds;

import java.util.ArrayList;
import java.util.List;

import com.pixelrifts.engine.base.GameObject;
import com.pixelrifts.engine.base.components.ColliderComponent;

public class InteractionWorld {
	private static List<ColliderComponent> colliders = new ArrayList<>();

	public static void update() {
		for (ColliderComponent c : colliders) {
			for (ColliderComponent c2 : colliders) {
				if (c.equals(c2))
					continue;
				if (c.testIntersect(c2)) {
					System.out.println("Intersects");
				}
			}
		}
	}

	public static void submitObject(GameObject collider) {
		colliders.add((ColliderComponent) collider.getComponent("Collider"));
	}

	public static void clear() {
		colliders.clear();
	}
}
