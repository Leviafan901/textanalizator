package com.epam.textanalizator.parser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;
import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;

public class SentenceParserTest {

	private final static String EMPTY_VALUE = null;
	private final static Value VALUE = new Value();
    private final static TextComposite SENTENCE = new TextComposite();
    private static SentenceParser sentenceParser;
    private static ValueParser valueParser;
    
    @BeforeClass
    public static void inizialise() {
    	VALUE.setContent("test");
    	VALUE.setType(ValueType.WORD);
    	SENTENCE.add(VALUE);
    	sentenceParser = new SentenceParser();
    	valueParser = new ValueParser();
    }
	
	@Test
	public void shouldReturnSentenceComponentWhenGetValidData() {
		// when
		sentenceParser.setProcessor(valueParser);
	    Component actualSentence = sentenceParser.parse("test");
        //then
	    Assert.assertEquals(actualSentence, SENTENCE);
    }
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenGetEmptyValue() {
		// when
		Component actual = valueParser.parse(EMPTY_VALUE);
	}
}
