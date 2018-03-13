package com.epam.textanalizator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentHandler {

	public static final String SPACE = " ";
	public static final String PUNCTUATION_PATTERN = "\\s+|\\^.$|\\^,$|\\!|\\?|\\^-$";
	public static final String SENTENCE_PATTERN = "[\\p{Upper}+\\-(](.(?!\\.))*..";
	public static final String PARAGRAPH_PATTERN = "\\t?[\\p{Upper}+\\-(](.(?!\\r?\\n\\r?\\n))*.";
	
	private static final String SYMBOL_REGEX = "(?!^)";
	private static final String WORD_PATTERN = "[a-zA-Z\\-]+[\\.,!\\?]?";
	private static final String MATH_PATTERN = "((\\d+|[ij])[(\\\\d+\\+\\-\\*/\\\\d+|(ij)]*)+";

	public static boolean matchContent(String content, String pattern) {
		boolean isNullContent = content == null;
		if (isNullContent) {
			throw new IllegalArgumentException("Incorrect content was detected.");
		}
		boolean isNullPattern = pattern == null;
		if (isNullPattern) {
			throw new IllegalArgumentException("Incorrect pattern was detected.");
		}
		Pattern currentPattern = Pattern.compile(pattern);
		Matcher matcher = currentPattern.matcher(content);
		return matcher.matches();
	}

	public static boolean isWord(String content) {
		return matchContent(content, WORD_PATTERN);
	}

	public static boolean isExpression(String content) {
		return matchContent(content, MATH_PATTERN);
	}

	public static int countSymbol(String word, String symbol) {
		String[] wordSymbols = word.split(SYMBOL_REGEX);
		int symbolCount = 0;
		for (int i = 0, length = wordSymbols.length; i < length; i++) {
			boolean hasSymbol = ContentHandler.matchContent(wordSymbols[i], symbol);
			if (hasSymbol) {
				symbolCount++;
			}
		}
		return symbolCount;
	}
}
