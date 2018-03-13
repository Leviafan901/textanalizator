package com.epam.textanalizator.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements Component {

	private List<Component> components = new ArrayList<Component>();

	public List<Component> getTextComponents() {
		return components;
	}
	
	public void add(Component component) {
		boolean isNull = component == null;
		if (isNull) {
			throw new IllegalArgumentException("Incorrect input text component.");
		}
		components.add(component);
	}

	public void addAll(List<Component> components) {
		boolean isNull = components == null;
		if (isNull) {
			throw new IllegalArgumentException("Incorrect input text component.");
		}
		this.components.addAll(components);
	}
	
	public void remove(Component component) {
		boolean isEmptyComponents = components.isEmpty();
		if (isEmptyComponents) {
			throw new IllegalArgumentException("Components are empty.");
		}
		boolean isValid = components.contains(component);
		if (isValid) {
			components.remove(component);
		}
	}

	@Override
	public String getContent() {
		int componentsSize = components.size();
		boolean isNullSize = componentsSize != 0;
		if (isNullSize) {
			StringBuilder result = new StringBuilder();
			for (Component textComponent : components) {
				result.append(textComponent.getContent());
			}
			return result.toString();
		} else {
			throw new IllegalArgumentException("Data is empty.");
		}
	}

	@Override
	public int compareTo(Component component) {
		List<Component> secondCollection = component.getTextComponents();
		int currentSize = this.components.size();
		int componentSize = secondCollection.size();
		return currentSize - componentSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((components == null) ? 0 : components.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		TextComposite otherObject = (TextComposite) object;
		if (components == null) {
			if (otherObject.components != null) {
				return false;
			}
		} else if (!components.equals(otherObject.components)) {
			return false;
		}
		return true;
	}
}
