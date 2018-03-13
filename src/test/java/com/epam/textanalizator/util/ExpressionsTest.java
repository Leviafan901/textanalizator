package com.epam.textanalizator.util;

import org.junit.Assert;
import org.junit.Test;

import com.epam.textanalizator.util.interpretor.DivideExpression;
import com.epam.textanalizator.util.interpretor.MinusExpression;
import com.epam.textanalizator.util.interpretor.MultiplyExpression;
import com.epam.textanalizator.util.interpretor.PlusExpression;

public class ExpressionsTest {

	private final static int FIRST_OPERATOR = 1;
	private final static int SECOND_OPERATOR = 1;
	
	@Test
	public void shouldDivideOperatorsAndReturnResultWhenGetValidData() {
		String expected = "1";
		//when
		String actual = new DivideExpression().interpret(FIRST_OPERATOR , SECOND_OPERATOR);
		//then
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldMinusOperatorsAndReturnResultWhenGetValidData() {
		String expected = "0";
		//when
		String actual = new MinusExpression().interpret(FIRST_OPERATOR , SECOND_OPERATOR);
		//then
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldPlusOperatorsAndReturnResultWhenGetValidData() {
		String expected = "2";
		//when
		String actual = new PlusExpression().interpret(FIRST_OPERATOR , SECOND_OPERATOR);
		//then
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldMultiplyOperatorsAndReturnResultWhenGetValidData() {
		String expected = "1";
		//when
		String actual = new MultiplyExpression().interpret(FIRST_OPERATOR , SECOND_OPERATOR);
		//then
		Assert.assertEquals(expected, actual);
	}
}
