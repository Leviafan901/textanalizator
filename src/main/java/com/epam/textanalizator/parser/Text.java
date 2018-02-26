package com.epam.textanalizator.parser;

import com.epam.textanalizator.composite.ComponentType;
import com.epam.textanalizator.composite.Symbol;
import com.epam.textanalizator.composite.SymbolType;
import com.epam.textanalizator.composite.TextComponent;
import com.epam.textanalizator.composite.TextComposite;
import com.epam.textanalizator.util.ContentMatcher;

public class Text implements Parser {

	private static final String SPLIT_IDENTIFIER = "\\n";

	private static Text textParser = null;
	private Parser nextParser;

	private Text() {

	}

	public static Text getInstance() {
		if (textParser == null) {
			textParser = new Text();
		}
		return textParser;
	}

	@Override
	public TextComponent parse(String content) {
		TextComposite text = new TextComposite();
		text.setComponentType(ComponentType.TEXT);
		setNextParser(ParagraphParser.getInstance());
		content = content.trim();

		Symbol newLine = new Symbol("\n", SymbolType.PUNCTUATION);
		Symbol tabulation = new Symbol("\t", SymbolType.PUNCTUATION);
		text.addTextComponent(tabulation);

		String[] paragraphs = content.split(SPLIT_IDENTIFIER);
		for (int i = 0, length = paragraphs.length; i < length; i++) {
			String currentContent = paragraphs[i];
			currentContent = currentContent.trim();
			TextComposite currentParagraph = (TextComposite) nextParser.parse(currentContent);

			text.addTextComponent(currentParagraph);

			if (i != length - ContentMatcher.LAST_ELEMENT_IDENTIFIER) {//
				text.addTextComponent(newLine);
				text.addTextComponent(tabulation);
			}
		}

		return text;
	}

	private void setNextParser(Parser nextParser) {
		this.nextParser = nextParser;
	}
}
