package com.epam.textanalizator.composite;

import java.util.List;

public class Value implements Component {

	private String content;
	private ValueType type;

	public Value() {

	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getContent() {
		return content;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}
	
	@Override
	public List<Component> getTextComponents() {
		throw new IllegalArgumentException("Incorrect operation, final leaf!");
	}
	
	@Override
	public int compareTo(Component value) {
		return content.compareTo(value.getContent());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Value otherObject = (Value) object;
		if (content == null) {
			if (otherObject.content != null) {
				return false;
			}
		} else if (!content.equals(otherObject.content)) {
			return false;
		}
		if (type != otherObject.type) {
			return false;
		}
		return true;
	}
}
