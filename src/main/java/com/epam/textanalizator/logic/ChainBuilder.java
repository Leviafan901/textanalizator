package com.epam.textanalizator.logic;

import com.epam.textanalizator.parser.ParagraphParser;
import com.epam.textanalizator.parser.Parser;
import com.epam.textanalizator.parser.SentenceParser;
import com.epam.textanalizator.parser.TextParser;
import com.epam.textanalizator.parser.ValueParser;

public class ChainBuilder {

	public static Parser buildChain() {
		Parser textParser = new TextParser();
		Parser paragraphParser = new ParagraphParser();
		textParser.setProcessor(paragraphParser);
	
		Parser sentenceParser = new SentenceParser();
		Parser valueParser = new ValueParser();
		sentenceParser.setProcessor(valueParser);
		paragraphParser.setProcessor(sentenceParser);
		return textParser;
	}
}
