package com.epam.textanalizator.parser;

import com.epam.textanalizator.composite.Component;

public abstract class Parser {

	protected Parser nextParser;

    public abstract Component parse(String content);

    public void setProcessor(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
