package com.pixelrifts.engine.objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import com.pixelrifts.engine.maths.Colour;
import com.pixelrifts.engine.utils.Cleaner;
import com.pixelrifts.engine.utils.MyFile;

public abstract class Shader {
	private int programId;
	private int vertexShaderId;
	private int fragmentShaderId;
	private FloatBuffer matbuffer = BufferUtils.createFloatBuffer(16);

	protected Shader(MyFile vertexFile, MyFile fragmentFile) {
		vertexShaderId = loadShader(vertexFile, GL_VERTEX_SHADER);
		fragmentShaderId = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
		programId = glCreateProgram();
		glAttachShader(programId, vertexShaderId);
		glAttachShader(programId, fragmentShaderId);
		glLinkProgram(programId);
		glValidateProgram(programId);
		getAllUniformLocations();
		Cleaner.addShader(this);
		glDetachShader(programId, vertexShaderId);
		glDetachShader(programId, fragmentShaderId);
		glDeleteShader(vertexShaderId);
		glDeleteShader(fragmentShaderId);
	}

	protected Shader(String v, String f) {
		vertexShaderId = loadShader(v, GL_VERTEX_SHADER);
		fragmentShaderId = loadShader(f, GL_FRAGMENT_SHADER);
		programId = glCreateProgram();
		glAttachShader(programId, vertexShaderId);
		glAttachShader(programId, fragmentShaderId);
		glLinkProgram(programId);
		glValidateProgram(programId);
		getAllUniformLocations();
		Cleaner.addShader(this);
		glDetachShader(programId, vertexShaderId);
		glDetachShader(programId, fragmentShaderId);
		glDeleteShader(vertexShaderId);
		glDeleteShader(fragmentShaderId);
	}

	protected int getUniformLocation(String name) {
		return glGetUniformLocation(programId, name);
	}

	public abstract void getAllUniformLocations();

	public void start() {
		glUseProgram(programId);
	}

	public void stop() {
		glUseProgram(0);
	}

	public void cleanUp() {
		stop();
		glDeleteProgram(programId);
	}

	protected void bindAttribute(int attrib, String variableName) {
		glBindAttribLocation(programId, attrib, variableName);
	}

	private static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
			String line;
			while ((line = reader.readLine()) != null)
				shaderSource.append(line).append("\n");
			reader.close();

		} catch (IOException e) {
			System.err.println("Coud not read File");
			e.printStackTrace();
			System.exit(-1);
		}

		int shaderId = glCreateShader(type);
		glShaderSource(shaderId, shaderSource);
		glCompileShader(shaderId);
		if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderId));
			System.err.println("Could not compile Shader.");
			System.exit(-1);
		}
		return shaderId;
	}

	private static int loadShader(MyFile file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = file.getReader();
			String line;
			while ((line = reader.readLine()) != null)
				shaderSource.append(line).append("\n");
			reader.close();

		} catch (IOException e) {
			System.err.println("Coud not read File");
			e.printStackTrace();
			System.exit(-1);
		}

		int shaderId = glCreateShader(type);
		glShaderSource(shaderId, shaderSource);
		glCompileShader(shaderId);
		if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderId));
			System.err.println("Could not compile Shader.");
			System.exit(-1);
		}
		return shaderId;
	}

	protected void loadFloat(int location, float value) {
		glUniform1f(location, value);
	}

	protected void loadInt(int location, int value) {
		glUniform1i(location, value);
	}

	protected void loadVector2(int location, Vector2f value) {
		glUniform2f(location, value.x, value.y);
	}

	protected void loadVector2(int location, float v1, float v2) {
		glUniform2f(location, v1, v2);
	}

	protected void loadVector3(int location, Vector3f value) {
		glUniform3f(location, value.x, value.y, value.z);
	}

	protected void loadColour(int location, Colour c) {
		glUniform4f(location, c.r, c.g, c.b, c.a);
	}

	protected void loadVector4(int location, Vector4f value) {
		glUniform4f(location, value.x, value.y, value.z, value.w);
	}

	protected void loadVector4(int location, float v1, float v2, float v3, float v4) {
		glUniform4f(location, v1, v2, v3, v4);
	}

	protected void loadMatrix(int location, Matrix4f value) {
		glUniformMatrix4fv(location, false, value.get(matbuffer));
	}

	protected void loadBoolean(int location, boolean value) {
		glUniform1f(location, value ? 1 : 0);
	}
}
