package com.epam.textanalizator.parser;

import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;
import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.util.ContentHandler;

public class ValueParser  extends Parser {

	@Override
    public void setProcessor(Parser nextParser) {
        throw new IllegalArgumentException("Final parser of chain. Operation not supported.");
    }

    @Override
    public Component parse(String content) {
    	boolean isNullContent = content == null;
        if (isNullContent) {
            throw new IllegalArgumentException("Incorrect content.");
        }
        Value value = new Value();
        boolean isWord = ContentHandler.isWord(content);
        if (isWord) {
        	value.setType(ValueType.WORD);
        }
        boolean isExpression = ContentHandler.isExpression(content);
        if (isExpression) {
        	value.setType(ValueType.MATH_EXPRESSION);
        }
        value.setContent(content);
        return value;
    }
}
