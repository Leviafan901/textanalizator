package com.epam.textanalizator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentMatcher {

	public static final int LAST_ELEMENT_IDENTIFIER = 1;
    public static final int SYMBOL_LENGTH = 1;

    public static final String SPACE = " ";
    public static final String EMPTY_SYMBOL = "";

    public static final String SYMBOL_ALPHABET_PATTERN = "[a-zA-z]";
    public static final String SYMBOL_NUMBER_PATTERN = "[0-9]";
    public static final String SYMBOL_MATH_PATTERN = "-|\\(|\\)|\\*|[+]|/";
    public static final String SYMBOL_PUNCTUATION_PATTERN = "\\s|\\.|,|!|\\?";

    public static final String LEXEME_WORD_PATTERN = "^[a-zA-Z]+[\\.,!\\?]?";

    public static boolean match(String component, String pattern) {
    	boolean isEmptyComponent = component.isEmpty();
    	if (isEmptyComponent) {
            throw new IllegalArgumentException("Incorrect content was detected.");
        }
    	boolean isEmptyPattern = pattern.isEmpty();
        if (isEmptyPattern) {
            throw new IllegalArgumentException("Incorrect pattern was detected.");
        }
        Pattern currentPattern = Pattern.compile(pattern);
        Matcher matcher = currentPattern.matcher(component);

        return matcher.matches();
    }
}