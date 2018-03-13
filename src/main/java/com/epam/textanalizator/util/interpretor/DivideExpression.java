package com.epam.textanalizator.util.interpretor;

public class DivideExpression implements Expression {

	@Override
	public String interpret(int firstValue, int secondValue) {
		return String.valueOf(firstValue / secondValue);
	}
}
