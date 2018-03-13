package com.epam.textanalizator.util;

import org.junit.Assert;
import org.junit.Test;

public class ContentHandlerTest {

	@Test
	public void shouldMatchContentAndReturnTrueWhenGetValidData() {
		// given
		String sentence = "This sentece is correct!";
		boolean expected = true;
		// when
		boolean actual = ContentHandler.matchContent(sentence, ContentHandler.SENTENCE_PATTERN);
		// then
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void shouldMatchWordAndReturnTrueWhenGetValidData() {
		// given
		String word = "Word";
		boolean expected = true;
		// when
		boolean actual = ContentHandler.isWord(word);
		// then
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void shouldMatchExpressionAndReturnTrueWhenGetValidData() {
		// given
		String expression = "3+4";
		boolean expected = true;
		// when
		boolean actual = ContentHandler.isExpression(expression);
		// then
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void shouldCountCharactersInWordAndReturnCountWhenGetValidData() {
		// given
		String word = "ffff";
		String symbol = "f";
		int expected = 4;
		// when
		int actual = ContentHandler.countSymbol(word, symbol);
		// then
		Assert.assertEquals(expected, actual);
	}
}
