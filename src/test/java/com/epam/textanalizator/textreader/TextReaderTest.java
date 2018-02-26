package com.epam.textanalizator.textreader;

import org.junit.Assert;
import org.junit.Test;

import com.epam.textanalixator.textreader.TextReader;
import com.epam.textanalizator.exceptions.DataReadingException;

public class TextReaderTest {

	private final static String RIGHT_PATH = "./src/test/resources/test.txt";
	private final static String INVALID_PATH = " ";
	private TextReader textReader = new TextReader();
	
	@Test
	public void shouldReturnStringOfTextComponentsWhenGetRightPath()
			throws DataReadingException {
		//when
		String actual = textReader.read(RIGHT_PATH);
		//then
		Assert.assertEquals(Text.EXPECTED, actual);
	}
	
	@Test (expected = DataReadingException.class)
	public void shouldThrowDataReadingExceptionWhenGetInvalidPath()
			throws DataReadingException {
		//when
		String actual = textReader.read(INVALID_PATH);
	}
}
