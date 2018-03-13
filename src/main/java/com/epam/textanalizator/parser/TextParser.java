package com.epam.textanalizator.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;
import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.util.ContentHandler;

public class TextParser extends Parser {

	@Override
	public Component parse(String content) {
		boolean isNullContent = content == null;
		if (isNullContent) {
			throw new IllegalArgumentException("Incorrect content.");
		}
		boolean isNullParser = nextParser == null;
		if (isNullParser) {
			Value text = new Value();
			text.setContent(content);
			return text;
		}
		Pattern pattern = Pattern.compile(ContentHandler.PARAGRAPH_PATTERN);
		Matcher matcher = pattern.matcher(content);
		int matcherGroupCount = matcher.groupCount();
		if (matcherGroupCount <= 0) {
			throw new IllegalArgumentException("Incorrect text format detected.");
		}
		TextComposite text = new TextComposite();
		while (matcher.find()) {
			String currentContent = matcher.group();
			currentContent = currentContent.trim();
			TextComposite paragraph = (TextComposite) nextParser.parse(currentContent);
			text.add(paragraph);
		}
		return text;
	}
}
