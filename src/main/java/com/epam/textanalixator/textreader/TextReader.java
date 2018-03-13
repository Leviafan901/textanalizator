package com.epam.textanalixator.textreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.epam.textanalizator.exceptions.DataReadingException;

public class TextReader {
	
	private final static String NEW_LINE = "\r";
	private final static String EMPTY_SYMBOL = "";

	public static String read(String filePath) throws DataReadingException {
        boolean isNullFilePath = filePath == null;
        boolean isEmptyFilePath = filePath.isEmpty();
		if (isNullFilePath | isEmptyFilePath) {
            throw new IllegalArgumentException("Incorrect file path.");
        }
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder text = new StringBuilder();
            while (fileReader.ready()) {
                char symbol = (char) fileReader.read();
                text.append(symbol);
            }

            String result = text.toString();
            result = result.replaceAll(NEW_LINE, EMPTY_SYMBOL);
            return result;
        } catch (IOException e) {
            throw new DataReadingException("Incorrect data.", e);
        }
    }
}
