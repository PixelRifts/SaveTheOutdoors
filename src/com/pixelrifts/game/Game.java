package com.pixelrifts.game;

import static com.pixelrifts.engine.basics.Display.*;

import org.joml.Vector2f;

import com.pixelrifts.engine.base.Animation;
import com.pixelrifts.engine.base.GameObject;
import com.pixelrifts.engine.base.components.AnimationComponent;
import com.pixelrifts.engine.base.components.ColliderComponent;
import com.pixelrifts.engine.base.components.Mesh2DComponent;
import com.pixelrifts.engine.base.components.Transform2DComponent;
import com.pixelrifts.engine.input.Mouse;
import com.pixelrifts.engine.maths.Colour;
import com.pixelrifts.engine.objects.Texture;
import com.pixelrifts.engine.utils.Buttons;

public class Game {
	public static void main(String[] args) {
		init();
		createWindow(1080, 720, "Outdoors");
		setBackground(Colour.DarkGrey());

		Animation a = new Animation(true, 100);
		a.setTexture(new Texture("./res/textures/smiley.png"), 1);
		Animation a2 = new Animation(true, 1000);
		a2.setTexture(new Texture("./res/textures/sadey.png"), 2);
		Animation a3 = new Animation(false, 1000);
		a3.setTexture(new Texture("./res/textures/okayey.png"), 2);
		AnimationComponent ac = new AnimationComponent();
		ac.addAnimation(a);
		ac.addAnimation(a2);
		ac.addAnimation(a3);
		ac.setCurrentAnimation(0);

		Mesh2DComponent c = new Mesh2DComponent(); c.setMesh(0, 0, 100, 100, new Texture("./res/textures/smiley.png"));
		Transform2DComponent c2 = new Transform2DComponent(); c2.translate(100, 0);
		ColliderComponent c3 = new ColliderComponent(); c3.setBounds(new Vector2f(-46, -46), new Vector2f(46, 46));
		
		Mesh2DComponent mc = new Mesh2DComponent(); mc.setMesh(0, 0, 100, 100, new Texture("./res/textures/smiley.png"));
		Transform2DComponent mc2 = new Transform2DComponent(); mc2.translate(10, -100);
		ColliderComponent mc3 = new ColliderComponent(); mc3.setBounds(new Vector2f(-46, -46), new Vector2f(46, 46));

		GameObject oneMore = new GameObject("Heya");
		oneMore.attachComponent(mc);
		oneMore.attachComponent(mc2);
		oneMore.attachComponent(mc3);
		
		GameObject object = new GameObject("Yeep");
		object.attachComponent(c);
		object.attachComponent(c2);
		object.attachComponent(c3);
		object.attachComponent(ac);

		while (isOpen()) {
			if (Mouse.instance.isButtonDown(Buttons.GLFW_MOUSE_BUTTON_LEFT)) {
				ac.setCurrentAnimation(2);
			} else if (Mouse.instance.isButtonDown(Buttons.GLFW_MOUSE_BUTTON_RIGHT)) {
				ac.setCurrentAnimation(1);
			}
			update();
		}

		destroyWindow();
		terminate();
	}
}
