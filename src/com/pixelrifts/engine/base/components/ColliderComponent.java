package com.pixelrifts.engine.base.components;

import org.joml.Vector2f;

import com.pixelrifts.engine.base.Component;

public class ColliderComponent extends Component {
	private Vector2f umin;
	private Vector2f min;
	private Vector2f umax;
	private Vector2f max;

	public ColliderComponent() {
		super("Collider");
		min = new Vector2f();
		max = new Vector2f();
	}

	@Override
	public void update() {
		if (object.hasComponent("Transform")) {
			Transform2DComponent transform = (Transform2DComponent) object.getComponent("Transform");
			max = new Vector2f(umax.x, umax.y);
			min = new Vector2f(umin.x, umin.y);
			max.add(transform.getPosition());
			min.add(transform.getPosition());
		}
	}

	public void setBounds(Vector2f min, Vector2f max) {
		this.umin = min;
		this.min = min;
		this.umax = max;
		this.max = max;
	}

	public boolean testIntersect(ColliderComponent o) {
		return (o.min.x > min.x && o.min.x < max.x)
				|| (o.max.x < max.x && o.max.x > min.x)
				&& (o.min.y > min.y && o.min.y < max.y)
				|| (o.max.y < max.y && o.max.y > min.y);
	}
}
