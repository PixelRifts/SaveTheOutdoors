package com.pixelrifts.engine.basics;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.pixelrifts.engine.input.Cursor;
import com.pixelrifts.engine.input.Keyboard;
import com.pixelrifts.engine.input.Mouse;
import com.pixelrifts.engine.input.MouseWheel;
import com.pixelrifts.engine.maths.Colour;
import com.pixelrifts.engine.rendering.ObjectRegistry;
import com.pixelrifts.engine.rendering.Renderer;
import com.pixelrifts.engine.utils.Cleaner;
import com.pixelrifts.engine.worlds.InteractionWorld;

public class Display {
	private static long win;
	private static int displaywidth, displayheight;
	private static float last = 0, now = System.nanoTime(), delta = 0;
	private static boolean hasResized;
	private static float time = 0;

	public static void init() {
		hasResized = false;
		if (!glfwInit()) {
			System.err.println("Could not initialize GLFW");
			System.exit(-1);
		}
	}

	public static void createWindow(int width, int height, String name) {
		win = glfwCreateWindow(width, height, name, 0, 0);
		displaywidth = width;
		displayheight = height;
		glfwMakeContextCurrent(win);
		GL.createCapabilities();

		glfwSetWindowSizeCallback(win, Display::onScreenResize);
		glfwSetMouseButtonCallback(win, Mouse.instance);
		glfwSetKeyCallback(win, Keyboard.instance);
		glfwSetCursorPosCallback(win, Cursor.instance);
		glfwSetScrollCallback(win, MouseWheel.instance);
	}

	private static void onScreenResize(long window, int width, int height) {
		displaywidth = width;
		displayheight = height;
		hasResized = true;
	}

	public static void clear(boolean colour, boolean depth, boolean stencil) {
		GL11.glClear((colour ? GL11.GL_COLOR_BUFFER_BIT : 0) | (depth ? GL11.GL_DEPTH_BUFFER_BIT : 0)
				| (stencil ? GL11.GL_STENCIL_BUFFER_BIT : 0));
	}

	public static boolean isOpen() {
		return !glfwWindowShouldClose(win);
	}

	public static void testResize() {
		if (hasResized) {
			Renderer.cam.calculateProjectionMatrix2D();
			GL11.glViewport(0, 0, displaywidth, displayheight);
		}
	}

	public static void update() {
		clear(true, false, false);
		testResize();
		Camera.instance.applyTransfer();

		ObjectRegistry.update();
		InteractionWorld.update();
		Renderer.render();

		hasResized = false;
		glfwPollEvents();
		glfwSwapBuffers(win);

		time++;
		last = now;
		now = System.nanoTime();
		delta = now - last;
	}

	public static float getCurrentTime() {
		return time;
	}

	public static void setBackground(Colour c) {
		GL11.glClearColor(c.r, c.g, c.b, c.a);
	}

	public static void setBackground(float r, float g, float b, float a) {
		GL11.glClearColor(r, g, b, a);
	}

	public static float getDelta() {
		return delta / 1000000000;
	}

	public static void destroyWindow() {
		Cleaner.cleanUp();
		glfwDestroyWindow(win);
	}

	public static void terminate() {
		glfwTerminate();
	}

	public static int getDisplayHeight() {
		return displayheight;
	}

	public static int getDisplayWidth() {
		return displaywidth;
	}

	public static boolean isResized() {
		return hasResized;
	}
}
