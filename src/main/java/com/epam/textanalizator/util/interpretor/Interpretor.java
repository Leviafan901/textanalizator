package com.epam.textanalizator.util.interpretor;

import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;

public class Interpretor {

	private static final String MINUS_OPERATOR = "-";
	private static final String PLUS_OPERATOR = "+";
	private static final String DIVIDE_OPERATOR = "/";
	private static final String MULTIPLY_OPERATOR = "*";
	private static final String CHARACTER_I = "i";
	private static final String CHARACTER_J = "j";

	private final static String MATH_EXPRESSION = "(?<=[-+*/])|(?=[-+*/])";
	private final static int FIRST_OPERATOR_POSITION = 0;
	private static final int MATH_OPERATOR_POSITION = 1;
	private final static int LAST_OPERATOR_POSITION = 2;

	public static Value calculateExpression(Value value, int i, int j) {
		String expression = value.getContent();
		String[] operators = expression.split(MATH_EXPRESSION);
		findSettedCharacters(i, j, operators);
		int firstOperator = Integer.valueOf(operators[FIRST_OPERATOR_POSITION]);
		int secondOperator = Integer.valueOf(operators[LAST_OPERATOR_POSITION]);
		String mathOperator = operators[MATH_OPERATOR_POSITION];
		String result = calculateResult(mathOperator, firstOperator, secondOperator);

		Value newValue = new Value();
		newValue.setContent(result);
		newValue.setType(ValueType.WORD);
		return newValue;
	}

	private static void findSettedCharacters(int i, int j, String[] operators) {
		for (int n = 0, length = operators.length; n < length; n++) {
			boolean isCharacterI = CHARACTER_I.equals(operators[n]);
			if (isCharacterI) {
				String valueI = String.valueOf(i);
				operators[n] = valueI;
			}
			boolean isCharacterJ = CHARACTER_J.equals(operators[n]);
			if (isCharacterJ) {
				String valueJ = String.valueOf(j);
				operators[n] = valueJ;
			}
		}
	}

	private static String calculateResult(String mathOperator, int firstValue, int secondValue) {
		String result = null;
		switch (mathOperator) {
		case DIVIDE_OPERATOR:
			result = new DivideExpression().interpret(firstValue, secondValue);
			break;
		case MINUS_OPERATOR:
			result = new MinusExpression().interpret(firstValue, secondValue);
			break;
		case PLUS_OPERATOR:
			result = new PlusExpression().interpret(firstValue, secondValue);
			break;
		case MULTIPLY_OPERATOR:
			result = new MultiplyExpression().interpret(firstValue, secondValue);
			break;
		default:
			throw new IllegalArgumentException("Incorrect math operator");
		}
		return result;
	}
}
