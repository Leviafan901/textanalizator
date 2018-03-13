package com.epam.textanalizator.parser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;
import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;

public class ParagraphParserTest {

	private static final String EMPTY_VALUE = null;
	private final static String CONTENT = "Test 3+i";
	private final static Value VALUE_WORD = new Value();
	private final static Value VALUE_EXPRESSION = new Value();
    private final static TextComposite SENTENCE = new TextComposite();
    private static SentenceParser sentenceParser;
    private static ValueParser valueParser;
    private final static TextComposite PARAGRAPH = new TextComposite();
    private static ParagraphParser paragraphParser;    
    
    @BeforeClass
    public static void inizialise() {
    	VALUE_WORD.setContent("Test");
    	VALUE_WORD.setType(ValueType.WORD);
    	SENTENCE.add(VALUE_WORD);
    	VALUE_EXPRESSION.setContent("3+i");
    	VALUE_EXPRESSION.setType(ValueType.MATH_EXPRESSION);
    	SENTENCE.add(VALUE_EXPRESSION);
    	sentenceParser = new SentenceParser();
    	valueParser = new ValueParser();
    	PARAGRAPH.add(SENTENCE);
    	paragraphParser = new ParagraphParser();
    }
	
	@Test
	public void shouldReturnParagraphComponentWhenGetValidData() {
		// when
		sentenceParser.setProcessor(valueParser);
		paragraphParser.setProcessor(sentenceParser);
	    Component actualParagraph = paragraphParser.parse(CONTENT);
        //then
	    Assert.assertEquals(actualParagraph, PARAGRAPH);
    }
	
	@Test
	public void shouldReturnValidComponentWhenGetValidData() {
		// given
		SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);
		Component expectedParagraph = new TextComposite();
		Component sentence = new TextComposite();
		// when
		Mockito.when(sentenceParser.parse(Mockito.anyString())).thenReturn(sentence);
		sentenceParser.setProcessor(valueParser);
		paragraphParser.setProcessor(sentenceParser);
	    Component actualParagraph = paragraphParser.parse("test");
        //then
	    Assert.assertEquals(actualParagraph, expectedParagraph);
    }
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenGetEmptyValue() {
		// when
		Component actual = valueParser.parse(EMPTY_VALUE);
	}
}
