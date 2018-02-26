package com.epam.textanalizator.parser;

import com.epam.textanalizator.composite.TextComponent;

public interface Parser {

	public TextComponent parse(String content);
}
