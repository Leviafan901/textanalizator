package com.epam.textanalizator.parser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;

public class ValueParserTest {

	private static String word = "Ipsum";
	private static String expression = "3+i";
	private final static String EMPTY_VALUE = null;
	private final static Value EXPECTED_WORD = new Value();
	private final static Value EXPECTED_EXPRESSION = new Value();
	private ValueParser valueParser = new ValueParser();

	@BeforeClass
	public static void initialize() {
		EXPECTED_WORD.setContent(word);
		EXPECTED_WORD.setType(ValueType.WORD);
		EXPECTED_EXPRESSION.setContent(expression);
		EXPECTED_EXPRESSION.setType(ValueType.MATH_EXPRESSION);
	}

	@Test
	public void shouldReturnWordWhenGetNotEmptyWordValue() {
		// when
		Component actual = valueParser.parse(word);
		// then
		Assert.assertEquals(actual, EXPECTED_WORD);
	}

	@Test
	public void shouldReturnExpressionWhenGetNotEmptyMathValue() {
		// when
		Component actual = valueParser.parse(expression);
		// then
		Assert.assertEquals(actual, EXPECTED_EXPRESSION);
	}

	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenGetEmptyValue() {
		// when
		Component actual = valueParser.parse(EMPTY_VALUE);
	}
}
