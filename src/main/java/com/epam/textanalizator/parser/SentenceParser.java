package com.epam.textanalizator.parser;

import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.util.ContentHandler;
import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;

public class SentenceParser extends Parser {

	@Override
	public Component parse(String content) {
		boolean isNullContent = content == null;
        if (isNullContent) {
            throw new IllegalArgumentException("Incorrect content.");
        }
		boolean isNullParser = nextParser == null;
		if (isNullParser) {
			Value sentence = new Value();
			sentence.setContent(content);
			return sentence;
		}
		TextComposite sentence = new TextComposite();
		String[] lexemes = content.split(ContentHandler.PUNCTUATION_PATTERN);
		for (int i = 0, length = lexemes.length; i < length; i++) {
			String currentContent = lexemes[i];
			Component value = nextParser.parse(currentContent);
			sentence.add(value);
		}

		return sentence;
	}
}
