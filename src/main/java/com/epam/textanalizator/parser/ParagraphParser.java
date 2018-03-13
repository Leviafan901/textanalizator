package com.epam.textanalizator.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.util.ContentHandler;
import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;

public class ParagraphParser extends Parser {

	@Override
	public Component parse(String content) {
		boolean isNullContent = content == null;
        if (isNullContent) {
            throw new IllegalArgumentException("Incorrect content.");
        }
        boolean isNullParser = nextParser == null;
		if (isNullParser) {
			Value paragraph = new Value();
			paragraph.setContent(content);
			return paragraph;
		}
		TextComposite paragraph = new TextComposite();
		Pattern pattern = Pattern.compile(ContentHandler.SENTENCE_PATTERN);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String currentContent = matcher.group();
			Component sentence = nextParser.parse(currentContent);
			paragraph.add(sentence);
		}
		return paragraph;
	}
}
