package com.pixelrifts.engine.utils;

import java.util.ArrayList;
import java.util.List;

import com.pixelrifts.engine.objects.IndexBuffer;
import com.pixelrifts.engine.objects.Shader;
import com.pixelrifts.engine.objects.Texture;
import com.pixelrifts.engine.objects.VertexArray;

public class Cleaner
{
	private static List<VertexArray> vaos;
	private static List<IndexBuffer> ibos;
	private static List<Texture> textures;
	private static List<Shader> shaders;
	
	static
	{
		vaos = new ArrayList<VertexArray>();
		ibos = new ArrayList<IndexBuffer>();
		textures = new ArrayList<Texture>();
		shaders = new ArrayList<Shader>();
	}

	public static void addVertexArray(VertexArray va)
	{
		vaos.add(va);
	}

	public static void addIndexBuffer(IndexBuffer ib)
	{
		ibos.add(ib);
	}

	public static void addTexture(Texture tex)
	{
		textures.add(tex);
	}

	public static void addShader(Shader shader)
	{
		shaders.add(shader);
	}
	
	public static void cleanUp()
	{
		for (VertexArray va : vaos)
			va.cleanUp();
		for (IndexBuffer ib : ibos)
			ib.cleanUp();
		for (Texture tex : textures)
			tex.cleanUp();
		for (Shader shader : shaders)
			shader.cleanUp();
	}
}
