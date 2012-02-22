package com.chamelaeon.numberstation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic implementation of {@link SpeakableTextConverter} that uses English for translation,
 * and the NATO phonetic alphabet.
 * 
 * @author Chamelaeon
 */
public class EnglishSpeakableTextConverter implements SpeakableTextConverter {

	/** Holds the phonetic translations for characters. */
	private static final Map<Character, String> PHONETIC_STRINGS = new HashMap<Character, String>();
	
	static {
		List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ');
		List<String> words = Arrays.asList("alpha", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", 
				"hotel", "india", "juliet", "kilo", "lima", "mike", "november", "oscar", "papa", "quebec", "romeo", 
				"sierra", "tango", "uniform", "victor", "whiskey", "x-ray", "yankee", "zulu", ".");
		
		for (int i = 0; i < chars.size(); i++) {
			PHONETIC_STRINGS.put(chars.get(i), words.get(i));
			PHONETIC_STRINGS.put(Character.toUpperCase(chars.get(i)), words.get(i));
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public String byteToText(byte input) {
		int intVal = Byte.valueOf(input).intValue();
		// Get it in the range 0-255 and turn it into octal.
		intVal += 128;
		char[] octalChars = Integer.toOctalString(intVal).toCharArray();
		
		// Always pad to three characters.
		if (3 == octalChars.length) {
			return octalChars[0] + ". " + octalChars[1] + ". " + octalChars[2];
		} else if (2 == octalChars.length) {
			return "0. " + octalChars[0] + ". " + octalChars[1];
		} else if (1 == octalChars.length) {
			return "0. 0. " + octalChars[0];
		} else {
			throw new RuntimeException("A byte converted to an octal string over 3 characters long!");
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<String> byteToText(byte... input) {
		List<String> retVal = new ArrayList<String>();
		for (byte inByte : input) {
			retVal.add(byteToText(inByte));
		}
		return retVal;
	}

	/** {@inheritDoc} */
	@Override
	public String intToText(int input) {
		if (input < 0) {
			throw new IllegalArgumentException("Input integers must be in the range [0-Integer.MAX_VALUE]!");
		}
		
		// Simple case.
		if (input < 10) {
			return Integer.toString(input);
		}
		
		// Integers in the range (9-MAX_VALUE).
		char[] intChars = Integer.toString(input).toCharArray();
		String retString = "";
		for (char c : intChars) {
			retString += (c + ".");
		}
		return retString;
	}

	/** {@inheritDoc} */
	@Override
	public List<String> intToText(int... input) {
		List<String> retVal = new ArrayList<String>();
		for (int inInt : input) {
			retVal.add(intToText(inInt));
		}
		return retVal;
	}
	
	/** {@inheritDoc} */
	@Override
	public String charToText(char input) {
		String retString = PHONETIC_STRINGS.get(input);
		if (null == retString) {
			throw new IllegalArgumentException("No phonetic word found for that character! Must be in the range a-zA-Z but was :" + input);
		}
		
		return retString;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<String> charToText(char... input) {
		List<String> retVal = new ArrayList<String>();
		for (char inChar : input) {
			retVal.add(charToText(inChar));
		}
		return retVal;
	}
}
