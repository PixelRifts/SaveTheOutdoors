package com.pixelrifts.engine.base;

public class Component {
	private String name;
	protected GameObject object;
	
	public void update() {}
	public void init() {}
	
	public Component(String name) {
		object = null;
		this.name = name;
	}

	public void setObject(GameObject object) {
		this.object = object;
	}

	public GameObject getObject() {
		return object;
	}
	
	public boolean compare(Component other) {
		return name == other.name;
	}
	
	public String getName() {
		return name;
	}
}
