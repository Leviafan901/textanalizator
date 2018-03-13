package com.epam.textanalizator.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;
import com.epam.textanalizator.util.interpretor.Interpretor;

public class InterpretorTest {

	private final static String EXPRESSION = "4/4";
	private final static String EXPECTED_RESULT = "1";
	private static Value valueResult;
	
	@BeforeClass
	public static void inizialise() {
		valueResult = new Value();
		valueResult.setContent(EXPECTED_RESULT);
		valueResult.setType(ValueType.WORD);
	}
	
	@Test
	public void shouldReturnExpectedValueWhenGetValidMathExpression() {
		//given
		Value testValue = new Value();
		testValue.setContent(EXPRESSION);
		testValue.setType(ValueType.MATH_EXPRESSION);
		//when
		Value actualValue = Interpretor.calculateExpression(testValue, 0, 0);
		//then
		Assert.assertEquals(valueResult, actualValue);
	}
}
