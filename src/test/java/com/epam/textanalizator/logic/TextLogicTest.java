package com.epam.textanalizator.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;
import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;

public class TextLogicTest {

	private static final int RESULT_INDEX = 0;
	private static TextLogic textLogic;

	@BeforeClass
	public static void inizialise() {
		textLogic = new TextLogic();
	}

	@Test
	public void shouldReturnParseredTextWithIntepretertedValuesWhenGetValidData() {
		// given
		String text = "Hello, 4/4 hi.\n";
		TextComposite expectedSentence = new TextComposite();
		Value firstValue = new Value();
		firstValue.setContent("Hello,");
		firstValue.setType(ValueType.WORD);
		expectedSentence.add(firstValue);
		Value secondValue = new Value();
		secondValue.setContent("1");
		secondValue.setType(ValueType.WORD);
		expectedSentence.add(secondValue);
		Value thirdValue = new Value();
		thirdValue.setContent("hi.");
		thirdValue.setType(ValueType.WORD);
		expectedSentence.add(thirdValue);
		// when
		List<Component> actualSentences = textLogic.restoreText(text, 0, 0);
		TextComposite sentence = (TextComposite) actualSentences.get(0);
		// then
		Assert.assertEquals(expectedSentence, sentence);
	}

	@Test
	public void shouldSwapLastAndFirstWordInGivenSentenceWhenIsValid() {
		// given
		String text = "Hello, 4/4 hi.\n";
		TextComposite expectedSentence = new TextComposite();
		Value firstValue = new Value();
		firstValue.setContent("hi.");
		firstValue.setType(ValueType.WORD);
		expectedSentence.add(firstValue);
		Value secondValue = new Value();
		secondValue.setContent("1");
		secondValue.setType(ValueType.WORD);
		expectedSentence.add(secondValue);
		Value thirdValue = new Value();
		thirdValue.setContent("Hello,");
		thirdValue.setType(ValueType.WORD);
		expectedSentence.add(thirdValue);
		// when
		List<Component> sentences = textLogic.restoreText(text, 0, 0);
		List<Component> result = textLogic.swapFirstAndLastWord(sentences);
		TextComposite actual = (TextComposite) result.get(RESULT_INDEX);
		// then
		Assert.assertEquals(expectedSentence, actual);
	}

	@Test
	public void shouldSortSentencesByLexemeSizeWhenIsValid() {
		// given
		TextComposite firstSentence = new TextComposite();
		Value firstValue = new Value();
		firstValue.setContent("hi.");
		firstValue.setType(ValueType.WORD);
		firstSentence.add(firstValue);
		Value secondValue = new Value();
		secondValue.setContent("1");
		secondValue.setType(ValueType.WORD);
		firstSentence.add(secondValue);
		Value thirdValue = new Value();
		thirdValue.setContent("Hello,");
		thirdValue.setType(ValueType.WORD);
		firstSentence.add(thirdValue);
		TextComposite secondSentence = new TextComposite();
		Value anotherFirstValue = new Value();
		anotherFirstValue.setContent("hi.");
		anotherFirstValue.setType(ValueType.WORD);
		secondSentence.add(firstValue);
		Value anotherSecondValue = new Value();
		anotherSecondValue.setContent("Hello,");
		anotherSecondValue.setType(ValueType.WORD);
		secondSentence.add(thirdValue);
		List<Component> expectedSentences = new ArrayList<>();
		expectedSentences.add(secondSentence);
		expectedSentences.add(firstSentence);
		// when
		List<Component> actual = textLogic.sortSentences(expectedSentences);
		// then
		Assert.assertEquals(expectedSentences, actual);
	}

	@Test
	public void shouldSortSentencesBySymbolWhenIsValid() {
		// given
		TextComposite sentence = new TextComposite();
		Value firstValue = new Value();
		firstValue.setContent("hhh");
		firstValue.setType(ValueType.WORD);
		sentence.add(firstValue);
		Value secondValue = new Value();
		secondValue.setContent("hh");
		secondValue.setType(ValueType.WORD);
		sentence.add(secondValue);
		List<Component> sentencesList = new ArrayList<>();
		sentencesList.add(sentence);
		List<Component> valueList = new ArrayList<>();
		valueList.add(firstValue);
		valueList.add(secondValue);
		String sortSymbol = "h";
		// when
		List<Component> actual = textLogic.sortBySymbol(sentencesList, sortSymbol);
		// then
		Assert.assertEquals(valueList, actual);
	}
}
