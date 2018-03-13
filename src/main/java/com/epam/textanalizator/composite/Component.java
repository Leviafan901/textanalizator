package com.epam.textanalizator.composite;

import java.util.List;

public interface Component extends Comparable<Component> {
	
    public abstract String getContent();//
    
    public List<Component> getTextComponents();//

	public int compareTo(Component value);
}
