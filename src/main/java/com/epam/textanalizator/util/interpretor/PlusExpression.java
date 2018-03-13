package com.epam.textanalizator.util.interpretor;

public class PlusExpression implements Expression {

	@Override
	public String interpret(int firstValue, int secondValue) {
		return String.valueOf(firstValue + secondValue);
	}
}
