package com.pixelrifts.engine.base;

import java.util.ArrayList;
import java.util.List;

import com.pixelrifts.engine.rendering.ObjectRegistry;

public class GameObject {
	private String name;
	private String tag;
	private String layer;

	private List<Component> components;

	public GameObject(String name) {
		this.name = name;
		tag = "untagged";
		layer = "default";
		components = new ArrayList<>();
		ObjectRegistry.submitGameObject(this);
	}

	public void update() {
		for (Component component : components)
			component.update();
	}

	public void attachComponent(Component c) {
		components.add(c);
		c.setObject(this);
		update();
	}

	public Component getComponent(String name) {
		for (Component component : components)
			if (name.equals(component.getName()))
				return component;
		return null;
	}

	public boolean hasComponent(String name) {
		for (Component component : components)
			if (name.equals(component.getName()))
				return true;
		return false;
	}
	
	public boolean hasComponent(Component c) {
		for (Component component : components)
			if (component.compare(c))
				return true;
		return false;
	}
	
	public void removeComponent(Component c) {
		components.remove(c);
	}
	
	public void removeComponent(String name) {
		for (Component c : components)
			if (c.getName().equals(name))
				components.remove(c);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}
}
