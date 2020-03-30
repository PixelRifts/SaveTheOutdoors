package com.pixelrifts.engine.rendering;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;

import com.pixelrifts.engine.base.GameObject;
import com.pixelrifts.engine.base.components.Mesh2DComponent;
import com.pixelrifts.engine.base.components.Transform2DComponent;
import com.pixelrifts.engine.basics.Camera;

public class SpriteRenderer {
	private static SpriteShader shader = new SpriteShader();
	
	public static void render(Camera cam, List<GameObject> objects) {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		shader.start();
		shader.loadPVMatrices(cam);
		for (GameObject obj : objects) {
			if (obj.hasComponent("Transform")) shader.loadTransformComponent((Transform2DComponent) obj.getComponent("Transform"));
			shader.loadMeshComponent((Mesh2DComponent) obj.getComponent("Mesh"));
			((Mesh2DComponent) obj.getComponent("Mesh")).render();
		}
		shader.stop();
	}
}
