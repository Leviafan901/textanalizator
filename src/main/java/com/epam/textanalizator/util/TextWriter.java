package com.epam.textanalizator.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.textanalizator.composite.Component;
import com.epam.textanalizator.composite.Value;

public class TextWriter {

	private final static Logger LOGGER = Logger.getLogger(TextWriter.class);

	public static void writeText(List<Component> sentences) {
		List<Component> values = new ArrayList<>();
		Iterator<Component> iteratorSentence = sentences.iterator();
		while (iteratorSentence.hasNext()) {
			Component sentence = iteratorSentence.next();
			List<Component> valueList = sentence.getTextComponents();
			values.addAll(valueList);
		}
		Iterator<Component> iterator = values.iterator();
		StringBuilder restoredText = new StringBuilder();
		while (iterator.hasNext()) {
			Value value = (Value) iterator.next();
			restoredText.append(value.getContent());
			restoredText.append(ContentHandler.SPACE);
		}
		String finalText = restoredText.toString();
		LOGGER.info(finalText);
	}
}
