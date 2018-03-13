package com.epam.textanalizator.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.TextComposite;
import com.epam.textanalizator.composite.Value;
import com.epam.textanalizator.composite.ValueType;
import com.epam.textanalizator.parser.Parser;
import com.epam.textanalizator.util.ContentHandler;
import com.epam.textanalizator.util.interpretor.Interpretor;

public class TextLogic {

	private static final int FIRST_VALUE = 0;

	public List<Component> sortSentences(List<Component> sentences) {
		Collections.sort(sentences, new Comparator<Component>() {
			@Override
			public int compare(Component firstSentence, Component secondSentence) {
				return firstSentence.compareTo(secondSentence);
			}
		});
		return sentences;
	}

	public List<Component> swapFirstAndLastWord(List<Component> sentences) {
		Iterator<Component> iteratorSentence = sentences.iterator();
		TextComposite newSentence = new TextComposite();
		List<Component> sentencesList = new ArrayList<>();
		while (iteratorSentence.hasNext()) {
			List<Component> values = getValues(iteratorSentence);
			int size = values.size();
			int lastValueIndex = size - 1;
			Value lastValue = (Value) values.get(lastValueIndex);
			Value firstValue = (Value) values.get(FIRST_VALUE);
			values.set(FIRST_VALUE, lastValue);
			values.set(lastValueIndex, firstValue);
			newSentence.addAll(values);
			sentencesList.add(newSentence);
		}
		return sentencesList;
	}

	public List<Component> sortBySymbol(List<Component> sentences, String symbol) {
		List<Component> values = new ArrayList<>();
		Iterator<Component> iteratorSentence = sentences.iterator();
		while (iteratorSentence.hasNext()) {
			values.addAll(getValues(iteratorSentence));
		}
		Collections.sort(values, new Comparator<Component>() {
			@Override
			public int compare(Component firstValue, Component secondValue) {
				String firstWord = firstValue.getContent();
				int firstWordSymbolCount = ContentHandler.countSymbol(firstWord, symbol);
				String secondWord = secondValue.getContent();
				int secondWordSymbolCount = ContentHandler.countSymbol(secondWord, symbol);
				boolean hasMoreSymbols = firstWordSymbolCount > secondWordSymbolCount;
				if (hasMoreSymbols) {
					return firstValue.compareTo(secondValue);
				} else if (!hasMoreSymbols) {
					return secondValue.compareTo(firstValue);
				}
				return 0;
			}
		});
		return values;
	}

	public List<Component> restoreText(String content, int i, int j) {
		List<Component> sentencesText = getSentences(content);
		List<Component> newValues = new ArrayList<>();
		List<Component> restoredSentences = new ArrayList<>();
		Iterator<Component> iteratorSentence = sentencesText.iterator();
		while (iteratorSentence.hasNext()) {
			List<Component> values = getValues(iteratorSentence);
			Iterator<Component> iterator = values.iterator();
			while (iterator.hasNext()) {
				Value value = (Value) iterator.next();
				ValueType type = value.getType();
				boolean isMathExpression = ValueType.MATH_EXPRESSION == type;
				if (isMathExpression) {
					Value mathValue = Interpretor.calculateExpression(value, i, j);
					newValues.add(mathValue);
				} else {
					newValues.add(value);
				}
			}
			TextComposite newSentence = new TextComposite();
			newSentence.addAll(newValues);
			restoredSentences.add(newSentence);
		}
		return restoredSentences;
	}

	private List<Component> getValues(Iterator<Component> iteratorSentence) {
		List<Component> values = new ArrayList<>();
		Component sentence = iteratorSentence.next();
		values.addAll(sentence.getTextComponents());
		return values;
	}

	private List<Component> getSentences(String content) {
		Parser parser = ChainBuilder.buildChain();
		TextComposite textComposite = (TextComposite) parser.parse(content);
		List<Component> paragraphs = textComposite.getTextComponents();
		List<Component> sentences = new ArrayList<>();
		for (Component paragraph : paragraphs) {
			sentences.addAll(paragraph.getTextComponents());
		}
		return sentences;
	}
}
