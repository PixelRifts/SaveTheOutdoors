package com.pixelrifts.engine.base.components;

import java.util.ArrayList;
import java.util.List;

import com.pixelrifts.engine.base.Animation;
import com.pixelrifts.engine.base.Component;
import com.pixelrifts.engine.basics.Display;

public class AnimationComponent extends Component {
	private List<Animation> animations;
	private Animation current;
	private Animation basic;

	public AnimationComponent() {
		super("Animation");
		animations = new ArrayList<>();
		current = null;
		basic = null;
	}

	@Override
	public void update() {
		if (object.hasComponent("Mesh")) {
			Mesh2DComponent comp = ((Mesh2DComponent) object.getComponent("Mesh"));
			if (!current.isActive()) {
				if (!basic.isActive())
					basic.setActive();
				basic.reset();
				current = basic;
			}
			comp.setTexture(current.getTexture());
			comp.setUvOffset(current.update(Display.getCurrentTime()));
		}
	}

	public void setCurrentAnimation(int index) {
		this.current = animations.get(index);
		this.current.reset();
	}
	
	public void addAnimation(Animation a) {
		animations.add(a);
		basic = animations.get(0);
		basic.reset();
	}
}
