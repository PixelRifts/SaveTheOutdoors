package com.pixelrifts.engine.objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.pixelrifts.engine.utils.Cleaner;
import com.pixelrifts.engine.utils.TextureUtils;

public class Texture {
	private int width, height;
	private int texture;
	private int texturebank;
	private String path;

	public Texture(String path) {
		this(path, false);
	}

	public Texture(String path, boolean flip) {
		stbi_set_flip_vertically_on_load(flip);
		this.path = path;
		load(path);
		Cleaner.addTexture(this);
	}

	private void load(String path) {
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);

		ByteBuffer data = stbi_load(path, w, h, comp, 4);
		texture = glGenTextures();

		this.width = w.get();
		this.height = h.get();

		glBindTexture(GL_TEXTURE_2D, texture);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		stbi_image_free(data);
	}

	public void bind() {
		texturebank = TextureUtils.getCurrentbank();
		glBindTexture(GL_TEXTURE_2D, texture);
	}

	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void cleanUp() {
		unbind();
		glDeleteTextures(texture);
	}

	public int getTextureID() {
		return texture;
	}

	public String getPath() {
		return path;
	}

	public int getTexturebank() {
		return texturebank;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "Texture [width=" + width + ", height=" + height + ", texture=" + texture + ", texturebank="
				+ texturebank + ", path=" + path + "]";
	}
}