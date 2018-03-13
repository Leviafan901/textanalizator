package com.epam.textanalizator.textreader;

import org.junit.Assert;
import org.junit.Test;

import com.epam.textanalixator.textreader.TextReader;
import com.epam.textanalizator.exceptions.DataReadingException;

public class TextReaderTest {

	public final static String EXPECTED = "It has survived - not only (five) centuries, but also the leap into 13+i electronic typesetting, remaining 3+5 essentially 9*3 unchanged. It was popularised in the 5-j with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of the Lorem Ipsum.\n" + 
			"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using 2*i Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" + 
			"It is a j*1200 established fact that a reader will be of a page when looking at its layout.\n" + 
			"Bye.";
	private final static String RIGHT_PATH = "./src/test/resources/test.txt";
	private final static String INVALID_PATH = " ";
	
	@Test
	public void shouldReturnStringOfTextComponentsWhenGetRightPath()
			throws DataReadingException {
		//when
		String actual = TextReader.read(RIGHT_PATH);
		//then
		Assert.assertEquals(EXPECTED, actual);
	}
	
	@Test (expected = DataReadingException.class)
	public void shouldThrowDataReadingExceptionWhenGetInvalidPath()
			throws DataReadingException {
		//when
		String actual = TextReader.read(INVALID_PATH);
	}
}
