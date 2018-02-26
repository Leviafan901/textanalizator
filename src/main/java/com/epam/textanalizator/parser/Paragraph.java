package com.epam.textanalizator.parser;

import com.epam.textanalizator.composite.ComponentType;
import com.epam.textanalizator.composite.Symbol;
import com.epam.textanalizator.composite.SymbolType;
import com.epam.textanalizator.composite.TextComponent;
import com.epam.textanalizator.composite.TextComposite;
import com.epam.textanalizator.util.ContentMatcher;

public class Paragraph implements Parser {

	private static final String SPLIT_IDENTIFIER = "\\.\\s";
    private static Paragraph paragraphParser = null;
    private Parser nextParser;

    private Paragraph() {
    	
    }

    public static Paragraph getInstance() {
        if (paragraphParser == null) {
            paragraphParser = new Paragraph();
        }
        return paragraphParser;
    }

    @Override
    public TextComponent parse(String content) {
        TextComposite paragraph = new TextComposite();
        paragraph.setComponentType(ComponentType.PARAGRAPH);

        setNextParser(SentenceParser.getInstance());

        Symbol space = new Symbol(ContentMatcher.SPACE, SymbolType.PUNCTUATION);

        String[] sentences = content.split(SPLIT_IDENTIFIER);
        for (int arrayIndex = 0; arrayIndex < sentences.length; arrayIndex++) {
            String currentContent = sentences[arrayIndex];
            currentContent += ".";

            TextComponent currentComponent = nextParser.parse(currentContent);
            paragraph.addTextComponent(currentComponent);

            if (arrayIndex != sentences.length - ContentMatcher.LAST_ELEMENT_IDENTIFIER) {
                paragraph.addTextComponent(space);
            }
        }

        return paragraph;
    }

    private void setNextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
