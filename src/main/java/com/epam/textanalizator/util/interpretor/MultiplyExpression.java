package com.epam.textanalizator.util.interpretor;

public class MultiplyExpression implements Expression {

	@Override
	public String interpret(int firstValue, int secondValue) {
		return String.valueOf(firstValue * secondValue);
	}
}
