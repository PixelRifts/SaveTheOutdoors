package com.pixelrifts.engine.rendering;

import org.joml.Vector4f;

import com.pixelrifts.engine.base.components.Mesh2DComponent;
import com.pixelrifts.engine.base.components.Transform2DComponent;
import com.pixelrifts.engine.basics.Camera;
import com.pixelrifts.engine.objects.Shader;
import com.pixelrifts.engine.utils.TextureUtils;

public class SpriteShader extends Shader {
	private int location_TextureSampler;
	private int location_ProjectionMatrix;
	private int location_ViewMatrix;
	private int location_TransformationMatrix;
	private int location_UVOffset;
	private int location_UVScale;

	public SpriteShader() {
		super("./res/shaders/sprite.vs", "./res/shaders/sprite.fs");
	}

	@Override
	public void getAllUniformLocations() {
		location_TextureSampler = super.getUniformLocation("u_textureSampler");
		location_ProjectionMatrix = super.getUniformLocation("u_projection");
		location_ViewMatrix = super.getUniformLocation("u_view");
		location_TransformationMatrix = super.getUniformLocation("u_transformation");
		location_UVOffset = super.getUniformLocation("u_uvOffset");
		location_UVScale = super.getUniformLocation("u_uvScale");
	}

	public void loadPVMatrices(Camera cam) {
		super.loadMatrix(location_ProjectionMatrix, cam.getProjMatrix());
		super.loadMatrix(location_ViewMatrix, cam.getViewMatrix());
	}

	public void loadTransformComponent(Transform2DComponent component) {
		super.loadMatrix(location_TransformationMatrix, component.toMatrix());
	}
	
	public void loadMeshComponent(Mesh2DComponent component) {
		TextureUtils.setTextureBank(1);
		component.getTexture().bind();
		Vector4f uvs = component.getUvOffset();
		super.loadVector2(location_UVOffset, uvs.x, uvs.y);
		super.loadVector2(location_UVScale, uvs.z, uvs.w);
		super.loadInt(location_TextureSampler, 1);
	}
}
