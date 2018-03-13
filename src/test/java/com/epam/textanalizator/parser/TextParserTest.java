package com.epam.textanalizator.parser;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;

public class TextParserTest {

	private static final String EMPTY_VALUE = null;
	public final static Component PARAGPAPH = new TextComposite();

	@Test
	public void shouldReturnTextCompositWhenGetValidData() {
		// given
		ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
		// when
		TextParser textParser = new TextParser();
		textParser.setProcessor(paragraphParser);
		Component actualText = textParser.parse("test");
		// then
		Assert.assertEquals(actualText, PARAGPAPH);

		Mockito.verifyNoMoreInteractions(paragraphParser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenInvalid() {
		// given
		ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
		// when
		TextParser textParser = new TextParser();
		textParser.setProcessor(paragraphParser);
		Component actualText = textParser.parse(EMPTY_VALUE);
	}
}
